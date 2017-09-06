/*
 * Copyright (c) 2017 Public Library of Science
 *
 * Permission is hereby granted, free of charge, to any person obtaining a
 * copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 */

package org.plos.maven;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

@Mojo(name = "docker-properties" )
public class DockerPropertyMojo extends AbstractMojo
{
  private static final String DOCKER_IP_HOST = "docker.ip.host";

  @Parameter( property = "docker-properties.skip", defaultValue = "false", alias = "docker-properties.skip" )
  private boolean skip;

  @Parameter( property = "docker-properties.container-name", defaultValue = "undefined", alias = "docker-properties.container-name" )
  private String name;

  public void execute() throws MojoExecutionException
  {
    if (skip) {
      getLog().info( "docker-properties.skip = true: ignoring docker properties" );
      return;
    }

    getLog().info("Resolving Docker Host");

    String dockerIp = getDockerIp(true);
    if (dockerIp != null) {
      setSystemProperty(DOCKER_IP_HOST, dockerIp);
    } else {
      dockerIp = getDockerIp(false);
      if (dockerIp != null) {
        setSystemProperty(DOCKER_IP_HOST, dockerIp);
      }
    }
  }

  private void setSystemProperty(String key, String value) {
      getLog().info(key+"="+value);
      System.setProperty(key, value);
  }

  private String getDockerIp(boolean dockerMachine) {
    try {
      Process process = null;
      if (dockerMachine) {
        process = new ProcessBuilder("docker-machine", "ip", "default").start();
      } else {
        process = new ProcessBuilder("docker", "inspect", "--format",
                                     "'{{ .NetworkSettings.IPAddress }}'", name).start();
      }
      BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
      String dockerIp = in.readLine();
      int pExitValue = process.waitFor();
      if (pExitValue == 0 && !isEmptyOrBlank(dockerIp)) {
        // ip string is delimited by single quotes. remove them.
        return dockerIp.replace("'","");
      }
    }
    catch (java.io.IOException | InterruptedException ex) {
      // an io exception will be thrown if program wasn't found. gobble it.
    }
    return null;
  }

  private boolean isEmptyOrBlank(String s) {
    return s == null || s.trim().isEmpty();
  }

  public static void main (String [] args)
  {
    try {
      new DockerPropertyMojo().execute();
      System.out.println(DOCKER_IP_HOST+"="+System.getProperty(DOCKER_IP_HOST));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

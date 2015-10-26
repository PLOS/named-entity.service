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
  private static final String DOCKER_INSTANCE_NAME = "neddb";

  @Parameter( property = "docker-properties.skip", defaultValue = "false", alias = "docker-properties.skip" )
  private boolean skip;

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
                                    "'{{ .NetworkSettings.IPAddress }}'", DOCKER_INSTANCE_NAME).start();
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

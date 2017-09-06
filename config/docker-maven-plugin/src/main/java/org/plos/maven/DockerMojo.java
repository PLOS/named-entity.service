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

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

@Mojo(name = "shutdown-hook" )
public class DockerMojo extends AbstractMojo
{
  @Parameter( property = "shutdown-hook.skip", defaultValue = "false", alias = "shutdown-hook.skip" )
  private boolean skip;

  @Parameter( property = "shutdown-hook.container-name", defaultValue = "undefined", alias = "shutdown-hook.container-name" )
  private String name;

  public void execute() throws MojoExecutionException
  {
    if (skip) {
      getLog().info( "shutdown-hook.skip = true: not installing shutdown hook" );
      return;
    }

    getLog().info("Registering Docker Shutdown Hook");
    Runtime.getRuntime().addShutdownHook(new Thread() {
      public void run() {
        // TODO: 1. accommodate boot2docker, 2. pass in instance name.
        getLog().info("Running Docker Shutdown Hook");
        try {
          String dockerStopCmd = "docker stop " + name;
          getLog().info(dockerStopCmd);
          Process p1 = Runtime.getRuntime().exec(dockerStopCmd);
          int p1ExitValue = p1.waitFor();
          if (p1ExitValue == 0) {
            String dockerRemoveCmd = "docker rm -v " + name;
            getLog().info(dockerRemoveCmd);
            Process p2 = Runtime.getRuntime().exec(dockerRemoveCmd);
            int p2ExitValue = p2.waitFor();
            if (p2ExitValue != 0) {
              getLog().warn(String.format("Unable to remove docker instance: %s exit: %d", name, p2ExitValue));
            }
          } else {
            getLog().warn(String.format("Unable to stop docker instance: %s exit: %d", name, p1ExitValue));
          }
        } catch (Exception e) {
          getLog().error("Problem shutting down Docker.", e);
        }
      }
    });
  }

  public static void main (String [] args)
  {
    try { new DockerMojo().execute(); } catch (Exception e) { e.printStackTrace(); }
  }
}

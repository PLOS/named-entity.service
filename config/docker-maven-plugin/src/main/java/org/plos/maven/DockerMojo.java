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
        String dockerInstance = "neddb";
        try {
          String dockerStopCmd = "docker stop " + dockerInstance;
          getLog().info(dockerStopCmd);
          Process p1 = Runtime.getRuntime().exec(dockerStopCmd);
          int p1ExitValue = p1.waitFor();
          if (p1ExitValue == 0) {
            String dockerRemoveCmd = "docker rm " + dockerInstance;
            getLog().info(dockerRemoveCmd);
            Process p2 = Runtime.getRuntime().exec(dockerRemoveCmd);
            int p2ExitValue = p2.waitFor();
            if (p2ExitValue != 0) {
              getLog().warn(String.format("Unable to remove docker instance: %s exit: %d", dockerInstance, p2ExitValue));
            }
          } else {
            getLog().warn(String.format("Unable to stop docker instance: %s exit: %d", dockerInstance, p1ExitValue));
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

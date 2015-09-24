package org.plos.maven;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

@Mojo(name = "docker-properties" )
public class DockerPropertyMojo extends AbstractMojo
{
  private static final String DOCKER_DB_HOST = "docker.db.host";

  @Parameter( property = "docker-properties.skip", defaultValue = "false", alias = "docker-properties.skip" )
  private boolean skip;

  public void execute() throws MojoExecutionException
  {
    if (skip) {
      getLog().info( "docker-properties.skip = true: ignoring docker properties" );
      return;
    }

    getLog().info("Resolving Docker Host");
    try {
      Process process = null;
      if (Files.exists(Paths.get("/usr/local/bin/docker-machine"), LinkOption.NOFOLLOW_LINKS)) {
        process = new ProcessBuilder("docker-machine", "ip", "neddb").start();
      }
      else if (Files.exists(Paths.get("/usr/bin/docker"), LinkOption.NOFOLLOW_LINKS)) {
        process = new ProcessBuilder("docker", "inspect", "--format", "'{{ .NetworkSettings.IPAddress }}'", "neddb").start();
      }
      BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
      String dockerIp = in.readLine();
      int pExitValue = process.waitFor();
      if (pExitValue == 0 && !isEmptyOrBlank(dockerIp)) {
        dockerIp = dockerIp.replace("'","");
        getLog().info(DOCKER_DB_HOST+"="+dockerIp);
        System.setProperty(DOCKER_DB_HOST, dockerIp);
      }
    } catch (Exception e) {
      getLog().error("Problem resolving docker host.", e);
    }
  }

  private boolean isEmptyOrBlank(String s) {
    return s == null || s.trim().isEmpty();
  }

  public static void main (String [] args)
  {
    try { new DockerPropertyMojo().execute(); } catch (Exception e) { e.printStackTrace(); }
  }
}

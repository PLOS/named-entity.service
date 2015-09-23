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

    getLog().info( "Adding Docker System Properties" );
    try {
      if (Files.exists(Paths.get("/usr/bin/docker"), LinkOption.NOFOLLOW_LINKS)) {
        String dockerIpCmd="docker inspect --format '{{ .NetworkSettings.IPAddress }}' neddb";
        getLog().info(dockerIpCmd);
        Process p = Runtime.getRuntime().exec(dockerIpCmd);
        int pExitValue = p.waitFor();
        if (pExitValue == 0) {
          BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
          String dockerIp = in.readLine();
          if ( !isEmptyOrBlank(dockerIp) ) {
            getLog().info(DOCKER_DB_HOST+"="+dockerIp);
            System.setProperty(DOCKER_DB_HOST, dockerIp);
          }
        }
      }
    } catch (Exception e) {
      getLog().error("Problem adding Docker system property.", e);
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

/*
 * Copyright (c) 2006-2014 by Public Library of Science
 * http://plos.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.plos.namedentity.rest;

import org.eclipse.persistence.jaxb.rs.MOXyJsonProvider;
import org.glassfish.jersey.server.ResourceConfig;

public class Main extends ResourceConfig {

  public Main() {
    // register jax-rs components
    packages("org.plos.namedentity.rest");

    MOXyJsonProvider moxyJsonProvider = new MOXyJsonProvider();
    moxyJsonProvider.setAttributePrefix("@");
    moxyJsonProvider.setFormattedOutput(true);
    moxyJsonProvider.setIncludeRoot(false);
    moxyJsonProvider.setMarshalEmptyCollections(false);
    moxyJsonProvider.setValueWrapper("$");

    register(moxyJsonProvider);

//    register(LoggingFilter.class);

    // set json provider properties here (moxy)
    //property(MarshallerProperties.JSON_INCLUDE_ROOT, false);

    System.out.println("NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNmdhyyssssssyhdNNNNNNNNNNNNNNNNN");
    System.out.println("NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNmyo+:-::::--::::-:::-:odNNNNNNNNNNNNN");
    System.out.println("NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNs:-::::::::::--:::--:::-::/NNNNNNNNNNNN");
    System.out.println("NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN/::::--:::::-:::///::----::-/NNNNNNNNNNN");
    System.out.println("NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNo:::::::.:+syhdddddddddyo:---+NNNNNNNNNNN");
    System.out.println("NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNd-:::::::oddddddddddddddddh:::mNNNNNNNNNNN");
    System.out.println("NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN/::::::::dddddddddddddddddds+NNNNNNNNNNNNN");
    System.out.println("NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNm-::::::-sdddddddddddddddddddsoNNNNNNNNNNNN");
    System.out.println("NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNo:::::::-hddddhsyyysshdddyyyyy/NNNNNNNNNNNN");
    System.out.println("NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN/:::::::-dddhsdMMMMMNhshymMMMMNyhNNNNNNNNNN");
    System.out.println("NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN-:::::::-dddoMMmNMMMMMd:MMMNsNMMomNNNNNNNNN");
    System.out.println("NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN.::::---.sss/MMosMMMMMh-hdmmyNMM+NNNNNNNNNN");
    System.out.println("NNNNNNNNNNNNNmNNNNNNNNNNNNNNNNNN.:+s+-::-dddhodMMMMMNysdhhyysshymNNNNNNNNNN");
    System.out.println("NNNNNNNNNNNosyssNNNNNNNNNNNNNNNN.+ho/-::-hddddysyyyssddddddddomNNNNNNNNNNNN");
    System.out.println("NNNNNhhhNNmoddddshNNNNNNNNNNNNNN.oddh-::-ydddddddddho+/://++/:+smNNNNNNNNNN");
    System.out.println("NNNm+hdhsyhmohdddsyNNNNNNNNNNNNN::ohhs:sshddddddds/---.--:-:---::+dNNNNNNNN");
    System.out.println("NNNNssddddhsy+hdddyyNNNNNhyyyNNNo:::-::ddddo--/+/::-:-:-::-::-:-::-yNNNNNNN");
    System.out.println("NNNNNmyshdddhshddddhsyysshddhoNNd-::::.hddd.   `--:::::::::::::::/oyNNNNNNN");
    System.out.println("NNhyyyyyooydddddddddddyhdddhomNNNy-:::-sddds`--```   `ss:hdddmNNNNNNNNNNNNN");
    System.out.println("NN+dddddhssdddddddhdddddddsyNNNNNNmo::-oddddho++:+/- `oyyshNNNNNNNNNNNNNNNN");
    System.out.println("NNdysshdddddddddddyosddddodNNNNNNNNNNNm+dddddddddhhyyhdddyoNNNNNNNNNNNNNNNN");
    System.out.println("NNNNNNhyysydddddddddyyddh/NNNNNNNNNNNNdoddddddddddddddhs+yNNNNNNNNNNNNNNNNN");
    System.out.println("NNNNNNNNNNhohdddddddhhy+::yNNNNNNNNNNhyysyhdddddddddddddosNNNNNNNNNNNNNNNNN");
    System.out.println("NNNNNNNNNNN-:+ossso+:://:-dNNNNNNNNNNommmdysssssssssoossymomNNNNNNNNNNNNNNN");
    System.out.println("NNNNNNNNNN+:::://///::::///NNNNNNNNNN/hmmmmmmmmmmmmoyosmmmm+NNNNNNNNNNNNNNN");
    System.out.println("NNNNNNNNNy//////:::://////oNNNNNNNmmo::+hmmmmmmmmmysddyommm::+oyhNNNNNNNNNN");
    System.out.println("NNNNNNNNN+///////////////-mNmhs+/::::::///ohmmmmmm/yhhs/+d//:-///:/oymNNNNN");
    System.out.println("NNNNNNNNN////////////////.+///////////::////:/+sss:--:--/:////-//////:/sdNN");

  }
}

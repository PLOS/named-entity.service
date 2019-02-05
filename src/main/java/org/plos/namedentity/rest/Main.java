/*
 * Copyright (c) 2014-2019 Public Library of Science
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

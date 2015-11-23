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
package org.plos.namedentity.api.adapter;

import java.io.IOException;
import java.io.Writer;
 
import org.eclipse.persistence.oxm.CharacterEscapeHandler;

/**
 * http://wiki.eclipse.org/EclipseLink/Release/2.4.0/JAXB_RI_Extensions/Character_Escape_Handler
 */
public class CharacterEscapeHandlerImpl implements CharacterEscapeHandler {

    public void escape(char[] buf, int start, int len, boolean isAttValue, Writer out) 
      throws IOException
    {
        for (int i = start; i < start + len; i++) {
            char ch = buf[i];

            if (ch == '&') {
                out.write("&amp;");
                continue;
            }

            if (ch == '"' && isAttValue) {
                out.write("&quot;");
                continue;
            }

            if (ch == '\'' && isAttValue) {
                out.write("&apos;");
                continue;
            }

            // TODO: handle other characters here

            // escape everything above ASCII to &#xXXXX;
            if (ch > 0x7F) {
                out.write("&#x");
                out.write(Integer.toHexString(ch));
                out.write(";");
                continue;
            }

            // otherwise print normally
            out.write(ch);
        }
    }
}

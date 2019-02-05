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

package org.plos.namedentity.api.entity;

import org.plos.namedentity.api.enums.NamedPartyEnum;

import javax.xml.bind.annotation.XmlTransient;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@XmlTransient
public abstract class Composite {

  abstract public Map<Class, List<? extends Entity>> readAsMap();

  abstract public void setFromMap(Map<Class, List<? extends Entity>> map);

  abstract public NamedPartyEnum getTypeName();

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;

    if (o == null || this.getClass() != o.getClass())
      return false;

    return Objects.equals(this.hashCode(), o.hashCode());
  }

  @Override
  public int hashCode() {

    Map<Class, List<? extends Entity>> compositeMap = readAsMap();

    Object[] array = new Object[compositeMap.size()];

    int i = 0;

    for (List<? extends Entity> entities : compositeMap.values()) {

      Integer sum = 0;

      if (entities != null)
        for (Entity entity : entities) {
          sum += entity.hashCode();
        }

      array[i++] = sum;
    }

    return Arrays.hashCode(array);
  }

}

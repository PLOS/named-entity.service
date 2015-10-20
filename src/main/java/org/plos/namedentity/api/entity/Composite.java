package org.plos.namedentity.api.entity;

import javax.xml.bind.annotation.XmlTransient;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@XmlTransient
public abstract class Composite {

  abstract public Map<Class, List<? extends Entity>> readAsMap();

  abstract public void setFromMap(Map<Class, List<? extends Entity>> map);

  abstract public String getTypeName();

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

package org.plos.namedentity.api.entity;

import javax.xml.bind.annotation.XmlTransient;
import java.util.List;
import java.util.Map;

@XmlTransient
public interface Composite {

  public Map<Class, List<? extends Entity>> getAsMap();

  public void setFromMap(Map<Class, List<? extends Entity>> map);

  public String getTypeName();

}

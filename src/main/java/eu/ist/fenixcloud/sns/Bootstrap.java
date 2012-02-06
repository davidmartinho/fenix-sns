package eu.ist.fenixcloud.sns;

import eu.ist.fenixcloud.sns.domain.SimpleNotificationService;
import pt.ist.fenixframework.Config;
import pt.ist.fenixframework.FenixFramework;

public class Bootstrap {

  public static void init() {
    try {
      FenixFramework.initialize(new Config() {{
        domainModelPath = PropertiesManager.getProperty("dml.filename");
        dbAlias = PropertiesManager.getProperty("sql.alias");
        dbUsername = PropertiesManager.getProperty("sql.username");
        dbPassword = PropertiesManager.getProperty("sql.password");
        rootClass = SimpleNotificationService.class;
      }});
    } catch(Error e) {
      
    }
  }
}

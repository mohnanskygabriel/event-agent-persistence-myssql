/*import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import eventagent.persistence.dao.mysql.MySQLAdminDAO;
import eventagent.persistence.dao.mysql.MySQLEventsSourceDAO;
import eventagent.persistence.entities.EventsSource;*/

//this class is only demonstrating the usage of this persistence layer
public class Application {
	
	
	/*public static void main(String[] args) {
		//initialize the Spring Application context
		ApplicationContext context = new ClassPathXmlApplicationContext("MySQLPersistenceBeans.xml");
		//get the dao defined in Bean
		MySQLEventsSourceDAO ESdao = (MySQLEventsSourceDAO) context.getBean("eventsSourceDAO");
		MySQLAdminDAO adminDao = (MySQLAdminDAO) context.getBean("adminDAO");
		
		//work with dao as you want
		EventsSource es = ESdao.getAllEventsSources().get(0);
		es.setDownloadFrequencyInHours(5);
		
		ESdao.update(es);
		
	}*/
	
}

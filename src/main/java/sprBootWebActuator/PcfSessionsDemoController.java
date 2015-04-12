package sprBootWebActuator;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class PcfSessionsDemoController {

	private static final String SESSION_KEY = "SESSION_KEY";	// a static key for the session value
	private static Logger logger = Logger.getLogger(PcfSessionsDemoController.class);
	
	
    @RequestMapping("/")									// root mapping
    public String index(HttpServletRequest request) {
    	String rtnStr = getAppInstanceId(request);			// get id header
    	rtnStr += " Greetings from PCF Sessions Demo"; 		// prepare log output
    	logger.info(rtnStr);
        return rtnStr;
    }

    @RequestMapping("/set")
    public String setSessionVal(HttpServletRequest request, HttpSession session) {
    	String rtnStr = getAppInstanceId(request);			// get id header
    	Integer sessionValue = (new Random()).nextInt(999); // make random value betw 0 and 998 to store
    	session.setAttribute(SESSION_KEY, sessionValue);	// store in session
    	rtnStr += " Set sessionValue:"+sessionValue;		// prepare log output
    	logger.info(rtnStr);
    	return rtnStr;
    }

    @RequestMapping("/get")
    public String getSession(HttpServletRequest request, HttpSession session) {
    	String rtnStr = getAppInstanceId(request);							// get id header
    	Integer sessionValue = (Integer) session.getAttribute(SESSION_KEY);	// get from session
    	rtnStr += " Got sessionValue:"+sessionValue;						// prepare log output
    	logger.info(rtnStr);
    	return rtnStr;
    }
    
    @RequestMapping("/kill")
    public String kill(HttpServletRequest request) {
    	String rtnStr = getAppInstanceId(request);			// get id header
    	rtnStr += " KILLED!";								// prepare log output
    	logger.info(rtnStr);
    	System.exit(-1); 									// KILL command
    	return rtnStr;										// won't get here
    }
    
    private String getAppInstanceId(HttpServletRequest request) {
    	String x = "AppInstance: "+request.getLocalAddr()+":"+request.getLocalPort()+"<hr/>";
    	return x;
    }
}

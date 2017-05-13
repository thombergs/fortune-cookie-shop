package de.adesso.cookies.edgeserver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.springframework.stereotype.Component;

import com.netflix.util.Pair;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/** Der Filter verhindert, dass die Location (Host und Port) im Response-
 *  Header sich von der Location im Request unterscheidet. Dafür ersetzt 
 *  er in allen Response-Headern im Feld Location den Host und Port mit 
 *  denen des Requests.
 *  Dies wird benötigt, damit der Reverse Proxy bei Angular-Applikationen 
 *  richtig funktioniert und alle Anfragen über den Gateway-Server laufen 
 *  und der Client nicht direkt zu dem Server mit der Angular-Applikation 
 *  weitergeleitet wird.
 */
@Component
public class PreventResponseLocationChangeZuulPostFilter extends ZuulFilter {

    private final String LOCATION_HEADER_NAME = "Location";
	
	@Override
	public String filterType() {
		return "post";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		RequestContext context = RequestContext.getCurrentContext();
        String portalHost = context.getRequest().getServerName();
        int portalPort = context.getRequest().getServerPort();
        List<Pair<String, String>> responseHeaders = context.getZuulResponseHeaders();
        
        for (Pair<String, String> entry: responseHeaders) {
            if (entry.first().equals(LOCATION_HEADER_NAME) && !entry.second().equals(portalHost)) {
                try {
                    URL locationOriginal = new URL(entry.second());
                    URL locationNew = new URL(locationOriginal.getProtocol(), portalHost, portalPort, locationOriginal.getFile());
                    entry.setSecond(locationNew.toString());
               	} catch(MalformedURLException e) {
                    return null;
                }
            }
        }

        return null;
	}
}

package br.com.todo.filter;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseFilter;

public class CrossDomainFilter implements ContainerResponseFilter {
    /**
     * Add the cross domain data to the output if needed
     * 
     * @param creq The container request (input)
     * @param cres The container request (output)
     * @return The output request with cross domain if needed
     */
    @Override
    public ContainerResponse filter(ContainerRequest creq, ContainerResponse cres) {
    	//estava apresentando erro, como se os valores estivessem duplicados
    	//String headerValue = creq.getHeaderValue("origin");
        cres.getHttpHeaders().putSingle("Access-Control-Allow-Origin", "*");
        String reqHead = creq.getHeaderValue("Access-Control-Request-Headers");
        if (null != reqHead && !reqHead.equals(null)) {
         	cres.getHttpHeaders().add("Access-Control-Allow-Headers", reqHead);
        }        /*cres.getHttpHeaders().putSingle("Access-Control-Allow-Credentials", "true");
        
        String reqHead = creq.getHeaderValue("Access-Control-Request-Headers");
        if (null != reqHead && !reqHead.equals(null)) {
         	cres.getHttpHeaders().add("Access-Control-Allow-Headers", reqHead);
        }
        //cres.getHttpHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization, strinfs-token, x-requested-with, userId");
//        cres.getHttpHeaders().add("Access-Control-Allow-Credentials", "true");
 * 
 */
        cres.getHttpHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        //cres.getHttpHeaders().add("Access-Control-Max-Age", "1209600");
        return cres;
    }
}

package com.iscae.alpha.pgp.locataire;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.stereotype.Component;

import com.iscae.alpha.pgp.TenantContexte;


//@Component
public class CurrentTenantUser  implements CurrentTenantIdentifierResolver{ 


 

	    private String defaultTenant ="alfaconseiltenantdb";

		@Override
		public String resolveCurrentTenantIdentifier() {
			  String t =  TenantContexte.getCurrentTenant();
		        if(t!=null){
		            return t;
		        } else {
		            return defaultTenant;
		        }
		}

		@Override
		public boolean validateExistingCurrentSessions() {
			// TODO Auto-generated method stub
			return true;
		}

}

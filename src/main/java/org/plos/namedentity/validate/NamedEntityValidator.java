package org.plos.namedentity.validate;

import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.core.Ordered;

import org.plos.namedentity.api.IndividualComposite;
import org.plos.namedentity.api.NedValidationException;
import org.plos.namedentity.api.dto.EmailDTO;
import org.plos.namedentity.api.dto.RoleDTO;

public class NamedEntityValidator implements Ordered {

    // allows us to control ordering of advice (ascending priority, 1 highest)
    private int order;
    
    public int  getOrder()          { return order;       }
    public void setOrder(int order) { this.order = order; }

    public Object validate(ProceedingJoinPoint call) throws Throwable {
        Object returnValue;

        /* ------------------------------------------------------------------ */
        /*  BEFORE-VALIDATION                                                 */
        /* ------------------------------------------------------------------ */

        Object[] args = call.getArgs();
        if (args != null && args.length > 0 && args[0] instanceof IndividualComposite) {
            // TODO - replace with chain of business rules.
            RoleDTO role = ((IndividualComposite) args[0]).getRole();
            if (role == null) {
                throw new NedValidationException("Validation Phase 1 Failure. No ROLE defined for individual.");
            }
        }

        /* ------------------------------------------------------------------ */
        /*  METHOD EXECUTION                                                  */
        /* ------------------------------------------------------------------ */

        returnValue = call.proceed();

        /* ------------------------------------------------------------------ */
        /*  AFTER-VALIDATION                                                  */
        /* ------------------------------------------------------------------ */

        //TODO - perform after-validation here.
        //TODO - *** REMOVE DEMO HACK *** 

        if (args != null && args.length > 0 && args[0] instanceof IndividualComposite) {
            for (EmailDTO email : ((IndividualComposite) args[0]).getEmails()) {
                if ("foo@bar.com".equals(email.getEmailaddress())) {
                    throw new NedValidationException("Validation Phase 2 Failure. foo@bar.com backdoor detected !!!");
                }
            }
        }

        return returnValue;
    }
}

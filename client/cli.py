"""Python command line client for NED

Usage:
  cli.py API METHOD [<arguments>...]

APIS:
  service
  typeclasses

Examples:
  service config
  service errorcodes
  typeclasses list
  typeclasses read 23
  individuals read_individual 23
  individuals find_individuals email emailaddress john@doe.com
  individuals get_addresses 1
  individuals get_address 1 1

Options:
  -h --help     Show this screen.
  --version     Show version.

"""

import swagger_client
from docopt import docopt

swagger_client.configuration.username = 'akita'
swagger_client.configuration.password = 'akita'

apiclient = swagger_client.ApiClient('http://localhost:8080', header_name="Authorization", header_value=swagger_client.configuration.get_basic_auth_token())

apis = {}
apis['service'] = swagger_client.ServiceApi(apiclient)
apis['typeclasses'] = swagger_client.TypeclassesApi(apiclient)
apis['individuals'] = swagger_client.IndividualsApi(apiclient)
apis['organizations'] = swagger_client.OrganizationsApi(apiclient)
apis['institutionsearch'] = swagger_client.InstitutionsearchApi(apiclient)

_method = lambda m, o : getattr(o,m)

if __name__ == '__main__':
    args = docopt(__doc__, version='1.x')

    api = args['API']
    method = args['METHOD']
    methodargs = args['<arguments>']
    print(args)

    print (_method(method, apis[api])(methodargs))

"""Python command line client for NED

Usage:
  cli.py [-i FILE] API [METHOD] [<arguments>...]

Examples:
  service config
  typeclasses list
  typeclasses read 12
  individuals find_individuals email emailaddress john@doe.com
  individuals get_address 1 1
  individuals create_individual -i composite.json

Options:
  -i FILE       The input file (or use '-' for stdin)
  -h --help     Show this screen.
"""

import ned_client
import inspect
import sys
import argparse
import pprint

ned_client.configuration.username = 'akita'
ned_client.configuration.password = 'akita'
# ned_client.configuration.debug = True

api_root='http://localhost:8080/v0'

swagger_spec=api_root + '/swagger.json'

apiclient = ned_client.ApiClient(api_root)

_method = lambda m, o : getattr(o,m)

def get_method_list():

    from pyswagger import SwaggerApp, SwaggerSecurity
    from pyswagger.contrib.client.requests import Client
    from pyswagger.utils import jp_compose

    app = SwaggerApp._create_(swagger_spec)
    client = Client()

    methods = list(app.op.keys())
    apis = dict()

    for m in methods:

        full_method = m.split('!##!')
        if full_method[0] in apis:
            apis[full_method[0]].add(full_method[1])
        else:
            apis[full_method[0]] = set()

        # apis.add(m.split('!##!')[0])

    # print('APIs: \n' + '\n'.join(apis))

    pprint.pprint (apis)

    return apis

    # sys.exit(0)


if __name__ == '__main__':

    parser = argparse.ArgumentParser(description='NED Swagger CLI')
    parser.add_argument('-f', help="Input file", required=False)
    parser.add_argument('api')
    parser.add_argument('method', nargs='?')
    parser.add_argument('--methodargs')

    args = parser.parse_args()

    # print (args)

    apiname = args.api
    method = args.method
    methodargs = "" #args.accumulate(args.methodargs)
    infile = args.f

    if apiname == 'help':

        from pyswagger import SwaggerApp, SwaggerSecurity
        from pyswagger.contrib.client.requests import Client
        from pyswagger.utils import jp_compose

        app = SwaggerApp._create_(swagger_spec)
        client = Client()

        methods = list(app.op.keys())
        apis = set()

        for m in methods:
            apis.add(m.split('!##!')[0])

        print('APIs: \n' + '\n'.join(apis))

        sys.exit(0)

    if args.method == None:

        from pyswagger import SwaggerApp, SwaggerSecurity
        from pyswagger.contrib.client.requests import Client
        from pyswagger.utils import jp_compose

        app = SwaggerApp._create_(swagger_spec)
        client = Client()

        all_methods = list(app.op.keys())
        method_set = set()

        for m in all_methods:
            full_method = m.split('!##!')
            if full_method[0] == apiname:
                method_set.add(full_method[1])

        print (apiname + ' methods: \n' + '\n'.join(sorted(method_set)))

        get_method_list()

        sys.exit(0)


    c = getattr(ned_client, apiname.capitalize() + "Api")

    # print(args)

    api = c(apiclient)

    if not method:

        members = inspect.getmembers(api, predicate=inspect.ismethod)

        # print "Methods:"

        for member in members:
            if member[0].find('__') == 0:
                continue
            m = getattr(c, member[0])
            args = inspect.getargspec(m).args
            del args[0]
            # print ("  " + apiname + " " + member[0] + "  " + (' '.join(['<'+x+'>' for x in args])))

    else:
        if infile:
            if infile == '-':
                fh = sys.stdin
            else:
                fh = open(infile, "r")
            body = fh.read()
            # print (body)
            fh.close()
            print(_method(method, api)(*methodargs, body=body))
        else:
            print(_method(method, api)(*methodargs))

"""Swagger CLI client"""

import ned_client   # TODO: replace with generic REST
import sys
import argparse

basicauth_user = 'akita'
basicauth_password = 'akita'
api_root = 'http://localhost:8080/v0'
swagger_spec = api_root + '/swagger.json'

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

        if not full_method[0] in apis:
            apis[full_method[0]] = set()

        apis[full_method[0]].add(full_method[1])

    return apis

def parse_args():

    parser = argparse.ArgumentParser(description='NED Swagger CLI')
    # parser.add_argument('-f', help="Input file", required=False)
    parser.add_argument('api', nargs='?')
    parser.add_argument('method', nargs='?')
    parser.add_argument('--methodargs')

    args = parser.parse_args()

    # print (args)

    # args.methodargs = args.accumulate(args.methodargs)
    # infile = args.f

    if args.api == None:
        print('APIs: \n\t' + '\n\t'.join(sorted(get_method_list().keys())))
        sys.exit(0)

    if args.method == None:
        print(args.api + ' methods: \n\t' + '\n\t'.join(sorted(get_method_list()[args.api])))
        sys.exit(0)

    return args

def rest_exec(args):

    ned_client.configuration.username = basicauth_user
    ned_client.configuration.password = basicauth_password
    ned_client.configuration.debug = False
    apiclient = ned_client.ApiClient(api_root)

    _method = lambda m, o : getattr(o,m)
    c = getattr(ned_client, args.api.capitalize() + "Api")
    api = c(apiclient)
    print(_method(args.method, api)())


if __name__ == '__main__':
    args = parse_args()
    rest_exec(args)

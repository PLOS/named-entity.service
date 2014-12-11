#!/usr/bin/env python2
'''
This Resource File sets variables that are used in individual
test cases. It eventually should be replaced with more robust, 
less static, variable definitions. 
'''

# General resources
# set friendly_testhostname to 'prod' to run suite against production
# Two fields need to be changed to support running tests in your loacl development
# environment, first, set friendly_testhostname to localhost, then correct the 
# base_url value if you are using a port or key different than 8081 and wombat.

friendly_testhostname = 'dpro'
if friendly_testhostname == 'prod':
  base_url = ''
elif friendly_testhostname == 'localhost':
  base_url = 'http://localhost:8081/wombat'
else:
  base_url = 'http://one-' + friendly_testhostname + '.plosjournals.org/wombat'
rhino_url = 'http://one-' + friendly_testhostname + '.plosjournals.org/v1/'
alm_url = 'http://alm.plos.org/'
alm_key = 'xgCZJ8Fd5so2VD5Yqw94'

journals = [ 
    {'journalKey': 'One',         'journalTitle': 'One',                         'journalAdID': 'a3ac9da4', 'journalZoneID': '345'}, 
    {'journalKey': 'Biology',     'journalTitle': 'Biology',                     'journalAdID': 'a035a937', 'journalZoneID': '333'}, 
    {'journalKey': 'Medicine',    'journalTitle': 'Medicine',                    'journalAdID': 'acb99019', 'journalZoneID': '349'}, 
    {'journalKey': 'Pathogens',   'journalTitle': 'Pathogens',                   'journalAdID': 'a9c6c347', 'journalZoneID': '343'}, 
    {'journalKey': 'CompBiol',    'journalTitle': 'Computational Biology',       'journalAdID': 'a7e3e24e', 'journalZoneID': '337'}, 
    {'journalKey': 'Genetics',    'journalTitle': 'Genetics',                    'journalAdID': 'af816654', 'journalZoneID': '339'}, 
    {'journalKey': 'Ntds',        'journalTitle': 'Neglected Tropical Diseases', 'journalAdID': 'a0253914', 'journalZoneID': '347'},
    {'journalKey': 'Collections', 'journalTitle': 'Collections',                 'journalAdID': 'ae7a713f', 'journalZoneID': '331'} 
]

sixjournals = [ 
    { 'journalKey': 'Biology',
      'journalTitle': 'Biology',
      'journalAdID': 'a035a937',
      'journalZoneID': '333',
      'journalTweetQuery': 'https://twitter.com/search/?q=%40plosbiology+OR+%23plosbiology+OR+%22PLOS+Bio%22+OR+%22PLOS+Biology%22+OR+%22plosbiology%22+OR+%22plosbio%22+lang%3Aen',
      'journalBlogTitle': 'PLOS Biologue',
      'journalBlogTarget': 'http://blogs.plos.org/biologue/',
      'journalOpenXSlotAAdID': 'ad72fae7',
      'journalOpenXSlotBAdID': 'a8c8cc52',
      'journalOpenXSlotCAdID': 'acdcac79',
      'journalOpenXSlotAZoneID': '1019',
      'journalOpenXSlotBZoneID': '1021',
      'journalOpenXSlotCZoneID': '1023'
    },
    { 'journalKey': 'Medicine',
      'journalTitle': 'Medicine',
      'journalAdID': 'acb99019',
      'journalZoneID': '349',
      'journalTweetQuery': 'https://twitter.com/search/?q=%40plosmedicine+OR+%23plosmedicine+OR+%22PLOS+Med%22+OR+%22PLOS+Medicine%22+OR+%22plosmedicine%22+OR+%22plosmed%22+lang%3Aen',
      'journalBlogTitle': 'PLOS Speaking of Medicine',
      'journalBlogTarget': 'http://blogs.plos.org/speakingofmedicine/',
      'journalOpenXSlotAAdID': 'a56e1148',
      'journalOpenXSlotBAdID': 'affbc699',
      'journalOpenXSlotCAdID': 'a8257134',
      'journalOpenXSlotAZoneID': '1025',
      'journalOpenXSlotBZoneID': '1027',
      'journalOpenXSlotCZoneID': '1029'
    },
    { 'journalKey': 'Pathogens',
      'journalTitle': 'Pathogens',
      'journalAdID': 'a9c6c347',
      'journalZoneID': '343',
      'journalTweetQuery': 'https://twitter.com/search/?q=%40plospathogens+OR+%23plospathogens+OR+journal.ppat.+OR+%22PLOS+Pathogens%22+OR+%22plospathogens%22+OR+%22journal.ppat.%22+lang%3Aen',
      'journalBlogTitle': 'PLOS Speaking of Medicine',
      'journalBlogTarget': 'http://blogs.plos.org/speakingofmedicine/',
      'journalOpenXSlotAAdID': 'a703d0df',
      'journalOpenXSlotBAdID': 'a827d8a9',
      'journalOpenXSlotCAdID': 'a4b02911',
      'journalOpenXSlotAZoneID': '1043',
      'journalOpenXSlotBZoneID': '1045',
      'journalOpenXSlotCZoneID': '1047'
    },
    { 'journalKey': 'CompBiol',
      'journalTitle': 'Computational Biology',
      'journalAdID': 'a7e3e24e',
      'journalZoneID': '337',
      'journalTweetQuery': 'https://twitter.com/search/?q=%40ploscompbiol+OR+%23ploscompbiol+OR+%22PLOS+CompBiol%22+OR+%22PLOS+CompBio%22+OR+%22PLOS+Computational+Biology%22+OR+%22ploscomputationalbiology%22+OR+%22ploscompbiol%22+OR+%22ploscompbio%22+lang%3Aen',
      'journalBlogTitle': 'PLOS Biologue',
      'journalBlogTarget': 'http://blogs.plos.org/biologue/',
      'journalOpenXSlotAAdID': 'a5705a26',
      'journalOpenXSlotBAdID': 'added294',
      'journalOpenXSlotCAdID': 'ab02c5c7',
      'journalOpenXSlotAZoneID': '1037',
      'journalOpenXSlotBZoneID': '1039',
      'journalOpenXSlotCZoneID': '1041'
    },
    { 'journalKey': 'Genetics',
      'journalTitle': 'Genetics',
      'journalAdID': 'af816654',
      'journalZoneID': '339',
      'journalTweetQuery': 'https://twitter.com/search/?q=%40plosgenetics+OR+%23plosgenetics+OR+%22PLOS+Gen%22+OR+%22PLOS+Genetics%22+OR+%22plosgenetics%22+lang%3Aen',
      'journalBlogTitle': 'PLOS Biologue',
      'journalBlogTarget': 'http://blogs.plos.org/biologue/',
      'journalOpenXSlotAAdID': 'ac8db1cb',
      'journalOpenXSlotBAdID': 'a7a2169f',
      'journalOpenXSlotCAdID': 'abf37429',
      'journalOpenXSlotAZoneID': '1031',
      'journalOpenXSlotBZoneID': '1033',
      'journalOpenXSlotCZoneID': '1035'
    },
    { 'journalKey': 'Ntds',
      'journalTitle': 'Neglected Tropical Diseases',
      'journalAdID': 'a0253914',
      'journalZoneID': '347',
      'journalTweetQuery': 'https://twitter.com/search/?q=%40plosntds+OR+%23plosntds+OR+%22PLOS+NTDs%22+OR+%22plosntds%22+lang%3Aen',
      'journalBlogTitle': 'PLOS Speaking of Medicine',
      'journalBlogTarget': 'http://blogs.plos.org/speakingofmedicine/',
      'journalOpenXSlotAAdID': 'a4378fa6',
      'journalOpenXSlotBAdID': 'a34ad5f4',
      'journalOpenXSlotCAdID': 'af4f7f8a',
      'journalOpenXSlotAZoneID': '1049',
      'journalOpenXSlotBZoneID': '1051',
      'journalOpenXSlotCZoneID': '1053'
    }
]

# Header resources
search_term = 'Retinal'
search_term_root = 'Retin'
searchterms = [ 'MicroRNA', 'Small interfering RNA', 'Transgenic animals', 'Transgenic plants', 'Trait loci', 'Flight mechanics (biology)', 'Hand, foot, and mouth disease', 'Ontology and logic', 'Immunity to infections', 'Microbial drug resistance', 'Stomates', 'Food habits']

# registration resources
non_existing_user_email = 'jgray1@plos.org'
existing_user_email = 'jgray@plos.org'
existing_user_pw = 'in|fury8'
new_user_id_local_base = 'sealresq'
new_user_id_domain = '@gmail.com'
new_user_id_index = 250


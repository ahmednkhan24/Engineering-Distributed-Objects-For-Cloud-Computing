# import libraries
from xml.etree import ElementTree
import csv

# define all foreign characters found within the dblp.dtd
parser = ElementTree.XMLParser()
parser.parser.UseForeignDTD(True)

parser.entity['ouml'] = 'o'
parser.entity['Agrave'] = 'A'
parser.entity['Aacute'] = 'A'
parser.entity['Acirc'] = 'A'
parser.entity['Atilde'] = 'A'
parser.entity['Auml'] = 'A'
parser.entity['Aring'] = 'A'
parser.entity['AElig'] = 'A'
parser.entity['Ccedil'] = 'C'
parser.entity['Egrave'] = 'E'
parser.entity['Eacute'] = 'E'
parser.entity['Ecirc'] = 'E'
parser.entity['Euml'] = 'E'
parser.entity['Igrave'] = 'I'
parser.entity['Iacute'] = 'I'
parser.entity['Icirc'] = 'I'
parser.entity['Iuml'] = 'I'
parser.entity['ETH'] = 'E'
parser.entity['Ntilde'] = 'N'
parser.entity['Ograve'] = 'N'
parser.entity['Oacute'] = 'O'
parser.entity['Ocirc'] = 'O'
parser.entity['Otilde'] = 'O'
parser.entity['Ouml'] = 'O'
parser.entity['Oslash'] = 'O'
parser.entity['Ugrave'] = 'U'
parser.entity['Uacute'] = 'U'
parser.entity['Ucirc'] = 'U'
parser.entity['Uuml'] = 'U'
parser.entity['Yacute'] = 'Y'
parser.entity['THORN'] = 'T'
parser.entity['szlig'] = 's'
parser.entity['agrave'] = 'a'
parser.entity['aacute'] = 'a'
parser.entity['acirc'] = 'a'
parser.entity['atilde'] = 'a'
parser.entity['auml'] = 'a'
parser.entity['aring'] = 'a'
parser.entity['aelig'] = 'a'
parser.entity['ccedil'] = 'c'
parser.entity['egrave'] = 'e'
parser.entity['eacute'] = 'e'
parser.entity['ecirc'] = 'e'
parser.entity['euml'] = 'e'
parser.entity['igrave'] = 'i'
parser.entity['iacute'] = 'i'
parser.entity['icirc'] = 'i'
parser.entity['iuml'] = 'i'
parser.entity['eth'] = 'e'
parser.entity['ntilde'] = 'n'
parser.entity['ograve'] = 'o'
parser.entity['oacute'] = 'o'
parser.entity['ocirc'] = 'o'
parser.entity['otilde'] = 'o'
parser.entity['ouml'] = 'o'
parser.entity['oslash'] = 'o'
parser.entity['ugrave'] = 'o'
parser.entity['uacute'] = 'u'
parser.entity['ucirc'] = 'u'
parser.entity['uuml'] = 'u'
parser.entity['yacute'] = 'y'
parser.entity['thorn'] = 't'
parser.entity['yuml'] = 'y'
parser.entity['reg'] = 'R'
parser.entity['micro'] = 'M'
parser.entity['times'] = 'T'

# location to read data from
input_file_name = 'dblp.xml'

# location to write data to
output_file_name = 'dblp.csv'

# create the xml element tree instance
etree = ElementTree.ElementTree()

# parse the xml file using the defined parser
tree = etree.parse(input_file_name, parser=parser)

# open the csv file as writable
with open(output_file_name, 'w') as csv_file:

    # create an object to write to csv file
    csv_writer = csv.writer(csv_file)

    # loop through each article tag
    for node in tree.iter('inproceedings'):

        # array to hold each author involved in a single publication
        data = []

        # loop through each author per publication
        for author in node.iter('author'):

            # add the author to the array
            data.append(author.text)

        # the publication is done and the authors have been saved, write to file
        csv_writer.writerow(data)

    for node in tree.iter('article'):
        # array to hold each author involved in a single publication
        data = []

        # loop through each author per publication
        for author in node.iter('author'):
            # add the author to the array
            data.append(author.text)

        # the publication is done and the authors have been saved, write to file
        csv_writer.writerow(data)
from bs4 import BeautifulSoup
import requests
import json
url= 'https://www.rottentomatoes.com/m/moana_2016'
content=requests.get(url).content
soup=BeautifulSoup(content,'xml')
tags= soup.findAll('script', {'id' :'jsonLdSchema'})
print(tags)
import urllib.request
import html2text

url = urllib.request.urlopen("http://100.65.133.55:8080/")
content = url.read()
print(content)
content = content.decode("utf-8") 
file = open('people_in_need.txt', 'w')
file.write(content)
file.close()
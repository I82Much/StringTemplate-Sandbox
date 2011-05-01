import sys


def makeJSFriendly(theFile):
  
  lines = theFile.readlines()
  lines = map (lambda x : x.replace("'", "\\'"), lines)
  
  return "+ \n".join([ "'%s\\n'" %x[:-1] for x in lines])
  

def main():
  for x in sys.argv[1:]:
    json = open("json/%s.json" %x)
    template = open("templates/%s.stg" %x)
    
    print makeJSFriendly(json)
    print makeJSFriendly(template)
    print "\n\n\n"
    
    json.close()
    template.close()
  

if __name__ == '__main__':
  main()
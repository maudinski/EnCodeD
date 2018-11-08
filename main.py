import sys
import time
from subprocess import call

n = sys.argv[1]

p = sys.argv[2]

if p == "mauricio.jpg":
	call(["bash", "test_mauricio.bash"])
else:
	call(["bash", "test_amatullah.bash"])

time.sleep(2)

f = open("answer.txt", "r")
n2 = f.readline()
if n2 == n:
	print("Faces matched")
	call(["bash", "enc_dec.bash"])
else:
	print("Faces didn't match")



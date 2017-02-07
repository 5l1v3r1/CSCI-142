import sys

file1 = [line for line in open(sys.argv[1])]
file2 = [line for line in open(sys.argv[2])]

print("Same number of lines?: " + str(len(file1) == len(file2)))
print("Same line length?: ", end='')

for i in range(len(file1)):
  line1 = file1[i]
  line2 = file2[i]
  Flag = True
  if len(line1) != len(line2):
    print ("FAILED line #" + str(i))
    Flag = False
  # print(str(Flag))

  Flag = False
  for j in range(len(line1)):
    if line1[j] != line2[j]:
      if not Flag:
        Flag = True
        print("DIFF LINE #" + str(i) + ": ")
      print(line1[j], end='')
  if Flag:
    print()



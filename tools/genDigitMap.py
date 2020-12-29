import re
file = open("digitRenderMap.java", "w")

file.write("private static final int[][] digitRenderMap = {\n")
for d in range(0, 10):
    file.write("{")
    for i in range(0, 7):
        for j in range(0, 4):

            if d == 0:
                if i == 0 or i == 6:
                    file.write("1,")
                elif j == 0 or j == 3:
                    file.write("1,")
                else:
                    file.write("0,")

            if d == 1:
                if j == 3:
                    file.write("1,")
                else:
                    file.write("0,")

            if d == 2:
                if i == 0 or i == 3 or i == 6:
                    file.write("1,")
                elif j == 3 and i < 3:
                    file.write("1,")
                elif j == 0 and i > 3:
                    file.write("1,")
                else:
                    file.write("0,")

            if d == 3:
                if i == 0 or i == 3 or i == 6:
                    file.write("1,")
                elif j == 3:
                    file.write("1,")
                else:
                    file.write("0,")

            if d == 4:
                if i == 3:
                    file.write("1,")
                elif j == 0 and i < 3:
                    file.write("1,")
                elif j == 3:
                    file.write("1,")
                else:
                    file.write("0,")

            if d == 5:
                if i == 0 or i == 3 or i == 6:
                    file.write("1,")
                elif j == 0 and i < 3:
                    file.write("1,")
                elif j == 3 and i > 3:
                    file.write("1,")
                else:
                    file.write("0,")

            if d == 6:
                if i == 0 or i == 3 or i == 6:
                    file.write("1,")
                elif j == 0 and i < 3:
                    file.write("1,")
                elif j == 0 or j == 3 and i > 3:
                    file.write("1,")
                else:
                    file.write("0,")

            if d == 7:
                if i == 0:
                    file.write("1,")
                elif j == 3:
                    file.write("1,")
                else:
                    file.write("0,")

            if d == 8:
                if i == 0 or i == 3 or i == 6:
                    file.write("1,")
                elif j == 0 or j == 3:
                    file.write("1,")
                else:
                    file.write("0,")

            if d == 9:
                if i == 0 or i == 3 or i == 6:
                    file.write("1,")
                elif j == 3:
                    file.write("1,")
                elif j == 0 and i < 3:
                    file.write("1,")
                else:
                    file.write("0,")
        if i == 6:
            file.write("},")
    if d != 9:
        file.write("\n")
file.write("}\n")
file.close()

with open("digitRenderMap.java", "r") as file:
    lines = file.readlines()

with open("digitRenderMap.java", "w") as file:
    for line in lines:
        file.write(re.sub(",}", "}", line))


pass

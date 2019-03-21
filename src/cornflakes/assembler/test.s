sw x6 0 x7 # storing address of CASE0 at location pointed by x7
#la x6 C1 # loading x6 with address of CASE1 (cond == 1)
sw x6 4 x7 # storing address of CASE1 at location pointed by x7 + 4
#la x6 C2 # loading x6 with address of default (cond != 0 or 1)
sw x6 8 x7  # storing address of default at location pointed by x7 + 8
#lb x5 cond # loading x5 with the input cond
#lw x20 var1
#lw x21 var2
addi x8 x0 0 # initializing
addi x9 x0 0 # initializing
addi x10 x0 0 # initializing
#bne x5 x0 case2 # checking if we satisfy CASE0
add x10 x10 x7 # get branch address into x10, instead of corrupting x7
lw x11 0 x10 # loading x11 with value (actually the address of CASE0)
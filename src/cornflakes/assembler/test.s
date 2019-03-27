addi x10 x0 5
addi x6 x0 5
addi x5 x0 1
addi x11 x0 1
jal x1 factorial
jal x0 exit
factorial:
beq x5 x10 j1
addi x2 x2 -8
sw x10 4 x2 
sw x1 0 x2 
addi x10 x10 -1
jal x1 factorial
addi x10 x10 1
mul x11 x10 x11
lw x10 4 x2 
lw x1 0 x2 
addi x2 x2 8
beq x10 x6 exit
jalr x0 0 x1 
jal x1 exit
j1:
jalr x0 0 x1 
exit:
addi x0 x0 0

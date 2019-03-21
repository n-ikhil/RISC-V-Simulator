top:
lw x1 1234 x17
sw x31 783 x5
addi x1 x1 1024
bottom:
beq x1 x9 top
jalr x1 991 x9
jal x1 bottom
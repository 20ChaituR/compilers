# Exercise 4 in the MIPS Lab
# Reads two numbers from the user and prints the product

   .text 0x00400000
   .globl main 
main:
   li $v0, 5            # Reads int into t1
   syscall
   move $t1, $v0
   
   li $v0, 5            # Reads int into t2
   syscall
   move $t2, $v0
   
   mul $a0, $t1, $t2    # Prints product of t1 and t2
   li $v0, 1
   syscall
   
   li $v0, 10 
   syscall              # End program
   
.data

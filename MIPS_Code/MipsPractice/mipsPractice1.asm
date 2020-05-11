   .text 0x00400000
   .globl main 
main:
   la $a0, intro        # Prints intro
   li $v0, 4
   syscall

   li $v0, 5            # Reads int into t1
   syscall
   move $t1, $v0
   
   li $v0, 5            # Reads int into t2
   syscall
   move $t2, $v0
   
   sub $t3, $t1, $t2    # t3 = t1 - t2
   
   bgtz $t3, positive   
   li $t4, 0
   sub $t3, $t4, $t3    # If t3 is negative, t3 = 0 - t3
   
   positive:
   
   la $a0, final1       # Print t3
   li $v0, 4
   syscall
   
   move $a0, $t1
   li $v0, 1
   syscall
   
   la $a0, final2
   li $v0, 4
   syscall
   
   move $a0, $t2
   li $v0, 1
   syscall
   
   la $a0, final3
   li $v0, 4
   syscall
   
   move $a0, $t3
   li $v0, 1
   syscall
   
   la $a0, final4
   li $v0, 4
   syscall
   
   li $v0, 10 
   syscall              # End program
   
.data
intro:
   .asciiz "Input two numbers on separate lines:\n"
final1:
   .asciiz "The difference of "
final2:
   .asciiz " and "
final3:
   .asciiz " is "
final4:
   .asciiz ".\n"
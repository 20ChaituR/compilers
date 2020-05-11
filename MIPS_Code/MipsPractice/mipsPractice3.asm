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
   
   li $v0, 5            # Reads int into t3
   syscall
   move $t3, $v0
   
   ble $t1,$t2,t12      # Put minimum into t4
   j t21
   t12:
   ble $t1,$t3,t123
   j t312
   t21:
   ble $t2,$t3,t213
   j t312
   t123:
   move $t4,$t1
   j print
   t213:
   move $t4,$t2
   j print
   t312:
   move $t4,$t3
   j print
   
   print:               # Print t4
   la $a0, final1
   li $v0, 4
   syscall
   
   move $a0, $t4
   li $v0, 1
   syscall
   
   la $a0, final2
   li $v0, 4
   syscall
   
   li $v0, 10 
   syscall              # End program
   
.data
intro:
   .asciiz "Input three numbers on separate lines:\n"
final1:
   .asciiz "The minimum is "
final2:
   .asciiz ".\n"

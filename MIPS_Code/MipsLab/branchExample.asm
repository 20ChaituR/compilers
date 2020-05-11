# Exercise 5 in the MIPS Lab
# Reads a number from the user and says whether it is even or odd
   
   .text 0x00400000
   .globl main 
main:
   la $a0, intro       # Prints intro
   li $v0, 4
   syscall

   li $v0, 5           # Read user input and put into t1
   syscall
   move $t1, $v0
   
   div $t2, $t1, 2     # Put (t1 / 2) * 2 into t2
   mul $t2, $t2, 2
   
   beq $t1, $t2, if
   else:
      la $a0, odd      # If t1 != t2, print Odd
      li $v0, 4
      syscall
      j finally
   if:
      la $a0, even     # If t1 = t2, print Even
      li $v0, 4
      syscall
   finally:
      li $v0, 10 
      syscall          # End program


   .data
intro:
   .asciiz "Enter a number, and I'll tell you if it's even or odd:\n"
even:
   .asciiz "Even"
odd:
   .asciiz "Odd"

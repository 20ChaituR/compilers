   .text 0x00400000
   .globl main 
main:
   lw $t1,x                # x = 2
   
   addu $t2,$t1,1          # y = x + 1
   sw $t2,y
   
   lw $t1,x                # x = x + y
   lw $t2,y
   addu $t3,$t1,$t2
   sw $t3,x
   
   lw $t1,x                # if x < y
   lw $t2,y
   bgt $t1,$t2,if1
   j endif1
   if1:
      lw $a0,y                   # WRITELN(y) 
      li $v0, 1
      syscall
      
      la $a0, endln
      li $v0, 4
      syscall
   endif1:
   
   while1:
   lw $t1,x
   bge $t1,10,endwhile1    # while x < 10
      lw $a0, x                  # WRITELN(x)
      li $v0, 1
      syscall
      
      la $a0, endln
      li $v0, 4
      syscall
   
      lw $t1,x                   # x = x + 1
      addu $t1,$t1,1
      sw $t1,x
      
      j while1
   endwhile1:
   
   li $v0, 10 
   syscall                 # End program
   
   .data
endln: .asciiz "\n"
x: .word 2
y: .word 

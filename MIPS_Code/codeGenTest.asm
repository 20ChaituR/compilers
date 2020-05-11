	# @author Chaitu Ravuri
	# @version 05/08/2020
	
	.text
	.globl main

main:
	# Pushes ra onto the stack
	subu $sp $sp 4
	sw $ra ($sp)

	# Sets v0 to 2
	li $v0 2

	# Pushes v0 onto the stack
	subu $sp $sp 4
	sw $v0 ($sp)

	# Sets v0 to 4
	li $v0 4

	# Pushes v0 onto the stack
	subu $sp $sp 4
	sw $v0 ($sp)

	# Pushes zero onto the stack
	subu $sp $sp 4
	sw $zero ($sp)

	# Jumps to procedure countUp
	jal proccountUp

	# Pops stack onto v0
	lw $v0 ($sp)
	addu $sp $sp 4

	# Pops stack onto t0
	lw $t0 ($sp)
	addu $sp $sp 4

	# Pops stack onto t0
	lw $t0 ($sp)
	addu $sp $sp 4

	# Pops stack onto ra
	lw $ra ($sp)
	addu $sp $sp 4

	# Assigns v0 to x
	la $t0 varx
	sw $v0 ($t0)

	# Halts the program
	li $v0 10
	syscall

proccountUp:
beginif0:
	# Puts count into v0
	lw $v0 8($sp)

	# Pushes v0 onto the stack
	subu $sp $sp 4
	sw $v0 ($sp)

	# Puts max into v0
	lw $v0 8($sp)

	# Pops stack onto t0
	lw $t0 ($sp)
	addu $sp $sp 4

	# If t0 > v0 go to endif0
	bgt $t0 $v0 endif0

	# Puts count into v0
	lw $v0 8($sp)

	# Prints v0
	move $a0 $v0
	li $v0 1
	syscall

	# Prints newline
	la $a0 newline
	li $v0 4
	syscall

	# Pushes ra onto the stack
	subu $sp $sp 4
	sw $ra ($sp)

	# Puts count into v0
	lw $v0 12($sp)

	# Pushes v0 onto the stack
	subu $sp $sp 4
	sw $v0 ($sp)

	# Sets v0 to 1
	li $v0 1

	# Pops stack onto t0
	lw $t0 ($sp)
	addu $sp $sp 4

	# Adds t0 and v0
	addu $v0 $t0 $v0

	# Pushes v0 onto the stack
	subu $sp $sp 4
	sw $v0 ($sp)

	# Puts max into v0
	lw $v0 12($sp)

	# Pushes v0 onto the stack
	subu $sp $sp 4
	sw $v0 ($sp)

	# Pushes zero onto the stack
	subu $sp $sp 4
	sw $zero ($sp)

	# Jumps to procedure countUp
	jal proccountUp

	# Pops stack onto v0
	lw $v0 ($sp)
	addu $sp $sp 4

	# Pops stack onto t0
	lw $t0 ($sp)
	addu $sp $sp 4

	# Pops stack onto t0
	lw $t0 ($sp)
	addu $sp $sp 4

	# Pops stack onto ra
	lw $ra ($sp)
	addu $sp $sp 4

	# Assigns v0 to ignore
	la $t0 varignore
	sw $v0 ($t0)

endif0:
	
	# Jumps back to procedure call
	jr $ra

	.data
newline:
	.asciiz "\n"
varignore:
	.word 0
varx:
	.word 0

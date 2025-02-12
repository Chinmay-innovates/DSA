import math;


def add(a, b) -> int:
	return a + b


def max1(a, b) -> int:
	return max(a, b)


def max2(a, b) -> int:
	return a if a > b else b


def fact(n):
	return 1 if n <= 1 else n * fact(n - 1)


def simpleInterest(p, t, r):
	return (p * t * r) / 100


def isArmstrong(n):
	num_digits = len(str(n))  # Count number of digits
	return n == sum(int(digit) ** num_digits for digit in str(n))


def areaOfCircle(r):
	return math.pi * r * r


def isPrime(n):
	if n < 2: return False
	if n == 2: return True
	if n % 2 == 0: return False

	for i in range(3, int(math.sqrt(n)) + 1, 2):
		if n % i == 0:
			return False
	return True


# O(b loglogb) 
def sieve_of_eratosthenes(a, b):
	if a > b:
		return []
	
	is_prime = [True] * (b - a + 1)
	is_prime[0] = is_prime[1] = False  # 0 and 1 are not prime numbers

	i = 2
	while i * i <= b:
		if is_prime[i]:
			for p in range(i * i, b + 1, i):
				is_prime[p] = False
		i += 1

	return [p for p in range(a, b + 1) if is_prime[p]]


def nth_fib(n):
	a, b = 0, 1
	for _ in range(n):
		a, b = b, a + b
	return a


def nth_multiple_in_fib(n, num, limit=1000):
	fib1, fib2 = 0, 1
	count = 0
	index = 1  # Fib series index

	while index < limit:
		if fib1 % num == 0:
			count += 1
			if count == n:
				return fib1
		fib1, fib2 = fib2, fib1 + fib2
		index += 1
	return -1


def get_ASCII(char):
	return ord(char)


def is_perfect_square(n):
	s = int(math.sqrt(n))
	return s * s == n


def sum_of_squares(n):
	return sum(i * i for i in range(1, n + 1))  # n(n+1)(2n+1)/6


def sum_of_cubes(n):
	return sum(i * i * i for i in range(1, n + 1))  # [n(n+1)/2]^2 


def is_fibonacci(n):
	if n < 0:
		return False
	return is_perfect_square(5 * n * n + 4) or is_perfect_square(5 * n * n - 4)

# --------- OTHER OPTIMAL METHODS ---------#


# Matrix Exponentiation O(logN)
def nth_fib_matix(n):
	if n == 0: return 0

	fib_matrix = [[1, 1], [1, 0]]

	result_matrix = matrix_pow(fib_matrix, n - 1)

	return result_matrix[0][0]


def matrix_pow(mat, power):
	result = [[1, 0], [0, 1]]  # Identity matrix
	while power > 0:
		if power % 2 != 0:
			result = matrix_mul(result, mat)
		mat = matrix_mul(mat, mat)
		power //= 2
	return result


def matrix_mul(A, B):
	size = len(A)
	C = [[0] * size for _ in range(size)]

	for i in range(size):
		for j in range(size):
			for k in range(size):
				C[i][j] += A[i][k] * B[k][j]

	return C


def simple_sieve(limit):
	is_prime = [True] * (limit + 1)
	is_prime[0] = is_prime[1] = False

	for p in range(2, int(math.sqrt(limit)) + 1):
		if is_prime[p]:
			for i in range(p * p, limit + 1, p):
				is_prime[i] = False

	return [p for p in range(2, limit + 1) if is_prime[p]]


# O(rootB loglog(rootB​) + (b−a) loglogb) 
def segmented_sieve(a, b):
	if a > b or a < 0 or b < 0:
		return []
	
	limit = int(math.sqrt(b)) + 1
	sieve = simple_sieve(limit)

	is_prime_range = [True] * (b - a + 1)

	for p in sieve:
		start = max(p * p, (a + p - 1) // p * p)

		for i in range(start, b + 1, p):
			is_prime_range[i - a] = False

	return [p for p in range(a, b + 1) if is_prime_range[p - a]  and p > 1]


if __name__ == '__main__':
	print("SUM is", add(1, 2))
	print("MAX_1 is", max1(1, 2))
	print("MAX_2 is", max2(1, 2))
	print("FACT is", fact(5))
	print("SI is", simpleInterest(1000, 2, 10))
	print("IS ARM_STRONG ", isArmstrong(153))
	print("IS ARM_STRONG ", isArmstrong(25))
	print("AREA OF CIRCLE ", areaOfCircle(5))
	
	print("Prime numbers between 10 and 50 ", sieve_of_eratosthenes(10, 50))
	print("Prime numbers between 10 and 50 ", segmented_sieve(10, 50))
	
	print("Nth fib", nth_fib(5))
	print("Nth fib matrix", nth_fib_matix(5))

	print("Is 10 a fib", is_fibonacci(10))
	print("Is 55 a fib", is_fibonacci(55))

	print("Nth=5 fib multiple", nth_multiple_in_fib(5, 3))  # Find the 5th multiple of 3 in Fib series
	
	print("ASCII of a", get_ASCII('a'))
	print("ASCII of a", get_ASCII('A'))

	print(sum_of_squares(5))
	print(sum_of_cubes(3))


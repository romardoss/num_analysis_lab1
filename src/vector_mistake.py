variant = 6
a = 0.2 * (variant - 5)
b = a
A = [
            [8.30, 2.62+a, 4.10, 1.90],
            [3.92, 8.45, 8.78-a, 2.46],
            [3.77, 7.21+a, 8.04, 2.28],
            [2.21, 3.65-a, 1.69, 6.99]
    ]
B = [-10.65+b, 12.21, 15.45-b, -8.35]
rounded = 4

# r = B - Ax
x = [-4.30489, -8.39292, 11.1963, 1.60194]
r = [0, 0, 0, 0]

for i in range(len(A)):
    buf = B[i]
    for j in range(len(x)):
        r[i] += A[i][j]*x[j]
    r[i] = round(buf-r[i], rounded)
print(r)

# r = B - Axm
xm = [-4.30489826998, -8.39292339458, 11.1963427229, 1.60194444725]
r = [0, 0, 0, 0]

for i in range(len(A)):
    buf = B[i]
    for j in range(len(x)):
        r[i] += A[i][j]*xm[j]
    r[i] = round(buf-r[i], rounded)
print(r)

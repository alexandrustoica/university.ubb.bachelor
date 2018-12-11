### CUDA Hello World

Applies a grayscale/greyscale filter over a normal png image.

### CUDA Setup

[MacOS](http://docs.nvidia.com/cuda/cuda-installation-guide-mac-os-x/index.html)

[Windows](http://docs.nvidia.com/cuda/cuda-installation-guide-microsoft-windows/index.html)

[Ubuntu](http://docs.nvidia.com/cuda/cuda-installation-guide-linux/index.html#ubuntu-installation)


### Run with Profiler
```
$ nvcc main.cu -o main.o -L/usr/local/lib -lpng -std=c++11 
$ nvprof ./main.o
```

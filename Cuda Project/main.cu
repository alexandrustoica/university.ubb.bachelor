#include <iostream>
#include <png++/png.hpp>
#include <cmath>
#include <chrono>


typedef struct pixel {
    
    png::byte red;
    png::byte blue;
    png::byte green;
    
    void from_rgb_pixel(png::rgb_pixel other) {
        this->red = other.red;
        this->blue = other.blue;
        this->green = other.green;
    }
    
    auto to_rgb_pixel() -> png::rgb_pixel const {
        return png::rgb_pixel(this->red, this->blue, this->green);
    }
} pixel;


__global__
void image_to_greyscale(pixel *pixels, pixel *result, size_t size) {
    for (size_t index = 0; index < size; index++) {
        pixel pixel = pixels[index];
        auto value = (png::byte) (sqrtf(
                    pixel.red * pixel.red +
                    pixel.blue * pixel.blue +
                    pixel.green * pixel.green) / 3);
        result[index] = {value, value, value};
    }
}


__global__
void image_to_negative(pixel *pixels, pixel *result, size_t size) {
    for (size_t index = 0; index < size; index++) {
        pixel pixel = pixels[index];
        result[index] = {
            (png::byte)(255 - pixel.red),
            (png::byte)(255 - pixel.blue),
            (png::byte)(255 - pixel.green)
        };
    }
}


int main() {

    png::image<png::rgb_pixel> original("original_image.png");
    pixel *pixels;
    pixel *result;

    size_t width = original.get_width();
    size_t height = original.get_height();

    cudaMallocManaged(&pixels, width * height * sizeof(pixel));
    cudaMallocManaged(&result, width * height * sizeof(pixel));

    for(size_t column_t = 0; column_t < width; column_t++) {
        for (size_t row_t = 0; row_t < height; row_t++) {
            pixels[column_t * height + row_t].from_rgb_pixel(
                    original.get_pixel(column_t, row_t));
        }
    }  

    auto start = std::chrono::system_clock::now();

    //image_to_negative<<<1, 1>>>(pixels, result, width * height);
    image_to_greyscale<<<1, height>>>(pixels, result, width * height);
    cudaDeviceSynchronize();
    
    auto end = std::chrono::system_clock::now();
    std::chrono::duration<double> time = end - start;
    std::cout << "Task took: " << time.count() << std::endl;

    for(size_t column_t = 0; column_t < width; column_t++)
        for(size_t row_t = 0; row_t < height; row_t++)
            original.set_pixel(column_t, row_t, result[column_t * height + row_t]
                .to_rgb_pixel());

    original.write("greyscale.png");
    cudaFree(pixels);
    cudaFree(result);
    return 0;
}


cmake_minimum_required(VERSION 3.4.1)

add_library(sft SHARED
        sft.c)

# Include libraries needed for sft lib
target_link_libraries(sft
        android
        log)

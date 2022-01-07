#include <string.h>
#include <jni.h>


JNIEXPORT jstring JNICALL
Java_com_example_softice_utils_Cpp_baseApi(JNIEnv *env, jclass clazz) {
    return (*env)->NewStringUTF(env, "https://softicetechnology.com/applicationdashbord/api/");
}
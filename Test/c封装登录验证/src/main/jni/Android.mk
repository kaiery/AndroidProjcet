    LOCAL_PATH := $(call my-dir)
    include $(CLEAR_VARS)
    LOCAL_MODULE    := checklogin
    LOCAL_SRC_FILES := checklogin.c
    include $(BUILD_SHARED_LIBRARY)
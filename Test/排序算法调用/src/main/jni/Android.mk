    LOCAL_PATH := $(call my-dir)
    include $(CLEAR_VARS)
    LOCAL_MODULE    := c_sortArray
    LOCAL_SRC_FILES := c_sortArray.c
    include $(BUILD_SHARED_LIBRARY)
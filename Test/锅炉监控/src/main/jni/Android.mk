    LOCAL_PATH := $(call my-dir)
    include $(CLEAR_VARS)
    LOCAL_MODULE    := guolu
    LOCAL_SRC_FILES := guolu.c

    LOCAL_LDLIBS += -llog


    include $(BUILD_SHARED_LIBRARY)
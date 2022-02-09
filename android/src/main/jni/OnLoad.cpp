#include <fbjni/fbjni.h>

#include "RNScreensComponentRegistry.h"

JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM *vm, void *) {
  return facebook::jni::initialize(vm, [] {
    // TODO: dvacca ramanpreet unify this with the way
    // "ComponentDescriptorFactory" is defined in Fabric
    
    facebook::react::RNScreensComponentRegistry::registerNatives();
  });
}
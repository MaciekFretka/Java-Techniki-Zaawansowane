#include "example_iloczyn.h"
#include <iostream>

JNIEXPORT jobject JNICALL Java_example_Iloczyn_multi01
(JNIEnv *env, jobject, jobjectArray arr, jobjectArray arrb) {
	
	

	jdouble A[10];
	jdouble B[10];
	jdouble result=0.0;
	jobject resultObj;
	jclass cls;
	
	for (int i = 0; i < 10; i++) {
	 jobject obj= env->GetObjectArrayElement(arr, i);
	 jobject objB = env->GetObjectArrayElement(arrb, i);
	 cls = env->GetObjectClass(obj);
	 jmethodID mid = env->GetMethodID(cls, "doubleValue","()D");
	 jdouble doubleA=env->CallDoubleMethod(obj, mid);
	 A[i] = doubleA;
	 jdouble doubleB = env->CallDoubleMethod(objB, mid);
	 B[i] = doubleB;
	}

	for (int i = 0; i < 10; i++) {
		jdouble temp = A[i] * B[i];
		result += temp;
	}

	jmethodID cid = env->GetMethodID(cls, "<init>", "(D)V");
	resultObj = env->NewObject(cls, cid, result);
	return resultObj;
}


JNIEXPORT jobject JNICALL Java_example_Iloczyn_multi02
(JNIEnv* env, jobject obj, jobjectArray arr) {
	//Pobranie z pola obiektu
	jclass cls = env->GetObjectClass(obj);
	jfieldID fid = env->GetFieldID(cls, "b", "[Ljava/lang/Double;");

	
	jobject objar=env->GetObjectField(obj, fid);
	jobjectArray* objArrb = reinterpret_cast<jobjectArray*>(&objar); 
	
	
	jdouble B[10];
	jdouble A[10];
	jdouble result = 0.0;
	jobject resultObj;

	for (int i = 0; i < 10; i++) {
		jobject obj = env->GetObjectArrayElement(*objArrb, i); // How to release it ?
		jobject objA = env->GetObjectArrayElement(arr, i);

	
		cls = env->GetObjectClass(obj);
		jmethodID mid = env->GetMethodID(cls, "doubleValue", "()D");
		
		jdouble doubleB = env->CallDoubleMethod(obj, mid);
		B[i] = doubleB;
		jdouble doubleA = env->CallDoubleMethod(objA, mid);
		A[i] = doubleA;
	}


	for (int i = 0; i < 10; i++) {
		jdouble temp = A[i] * B[i];
		result += temp;
	}

	




	jmethodID cid = env->GetMethodID(cls, "<init>", "(D)V");
	resultObj = env->NewObject(cls, cid, result);
	return resultObj;
}

JNIEXPORT void JNICALL Java_example_Iloczyn_multi03
(JNIEnv* env, jobject obj) {
	jclass clsob = env->GetObjectClass(obj);
	jfieldID fid = env->GetFieldID(clsob, "c", "Ljava/lang/Double;");
	jobject objC = env->GetObjectField(obj,fid);
	jclass cls = env->GetObjectClass(objC);
	std::cout << "Wprowadz kolejno wartosci wektora A: " << std::endl;
	jobjectArray vectorA = env->NewObjectArray(5,cls,NULL); // Nale¿y to zwolniæ
	for (int i = 0; i < 5; i++) {
		jdouble AValue;
		std::cin >> AValue;

		jmethodID cid = env->GetMethodID(cls, "<init>", "(D)V");
		jobject AValueObject = env->NewObject(cls,cid,AValue);
		env->SetObjectArrayElement(vectorA, i, AValueObject);
	}

	jfieldID fida = env->GetFieldID(clsob, "a", "[Ljava/lang/Double;");
	jobject *objArrA = reinterpret_cast<jobject*>(&vectorA);
	env->SetObjectField(obj, fida, *objArrA);

	std::cout << "Wprowadz kolejno wartosci wektora B: " << std::endl;
	jobjectArray vectorB = env->NewObjectArray(5, cls, NULL); // Nale¿y to zwolniæ
	for (int i = 0; i < 5; i++) {
		jdouble BValue;
		std::cin >> BValue;

		jmethodID cid = env->GetMethodID(cls, "<init>", "(D)V");
		jobject BValueObject = env->NewObject(cls, cid, BValue);
		env->SetObjectArrayElement(vectorB, i, BValueObject);
	}

	jfieldID fidb = env->GetFieldID(clsob, "b", "[Ljava/lang/Double;");
	jobject* objArrB = reinterpret_cast<jobject*>(&vectorB);
	env->SetObjectField(obj, fidb, *objArrB);

	jmethodID mid = env->GetMethodID(clsob, "multi04","()V");
	env->CallVoidMethod(obj,mid);
}
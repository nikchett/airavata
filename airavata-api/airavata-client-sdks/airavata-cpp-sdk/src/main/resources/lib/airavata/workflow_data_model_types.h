/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * Autogenerated by Thrift Compiler (0.9.2)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
#ifndef workflow_data_model_TYPES_H
#define workflow_data_model_TYPES_H

#include <iosfwd>

#include <thrift/Thrift.h>
#include <thrift/TApplicationException.h>
#include <thrift/protocol/TProtocol.h>
#include <thrift/transport/TTransport.h>

#include <thrift/cxxfunctional.h>
#include "application_io_models_types.h"
#include "airavata_commons_types.h"




class Workflow;

typedef struct _Workflow__isset {
  _Workflow__isset() : graph(false), image(false), workflowInputs(false), workflowOutputs(false) {}
  bool graph :1;
  bool image :1;
  bool workflowInputs :1;
  bool workflowOutputs :1;
} _Workflow__isset;

class Workflow {
 public:

  static const char* ascii_fingerprint; // = "5BEAC53013828D81DABDECEEE0E170C2";
  static const uint8_t binary_fingerprint[16]; // = {0x5B,0xEA,0xC5,0x30,0x13,0x82,0x8D,0x81,0xDA,0xBD,0xEC,0xEE,0xE0,0xE1,0x70,0xC2};

  Workflow(const Workflow&);
  Workflow& operator=(const Workflow&);
  Workflow() : templateId("DO_NOT_SET_AT_CLIENTS"), name(), graph(), image() {
  }

  virtual ~Workflow() throw();
  std::string templateId;
  std::string name;
  std::string graph;
  std::string image;
  std::vector< ::apache::airavata::model::application::io::InputDataObjectType>  workflowInputs;
  std::vector< ::apache::airavata::model::application::io::OutputDataObjectType>  workflowOutputs;

  _Workflow__isset __isset;

  void __set_templateId(const std::string& val);

  void __set_name(const std::string& val);

  void __set_graph(const std::string& val);

  void __set_image(const std::string& val);

  void __set_workflowInputs(const std::vector< ::apache::airavata::model::application::io::InputDataObjectType> & val);

  void __set_workflowOutputs(const std::vector< ::apache::airavata::model::application::io::OutputDataObjectType> & val);

  bool operator == (const Workflow & rhs) const
  {
    if (!(templateId == rhs.templateId))
      return false;
    if (!(name == rhs.name))
      return false;
    if (__isset.graph != rhs.__isset.graph)
      return false;
    else if (__isset.graph && !(graph == rhs.graph))
      return false;
    if (__isset.image != rhs.__isset.image)
      return false;
    else if (__isset.image && !(image == rhs.image))
      return false;
    if (__isset.workflowInputs != rhs.__isset.workflowInputs)
      return false;
    else if (__isset.workflowInputs && !(workflowInputs == rhs.workflowInputs))
      return false;
    if (__isset.workflowOutputs != rhs.__isset.workflowOutputs)
      return false;
    else if (__isset.workflowOutputs && !(workflowOutputs == rhs.workflowOutputs))
      return false;
    return true;
  }
  bool operator != (const Workflow &rhs) const {
    return !(*this == rhs);
  }

  bool operator < (const Workflow & ) const;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);
  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

  friend std::ostream& operator<<(std::ostream& out, const Workflow& obj);
};

void swap(Workflow &a, Workflow &b);



#endif

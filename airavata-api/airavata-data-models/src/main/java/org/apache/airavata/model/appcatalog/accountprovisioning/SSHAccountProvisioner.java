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
 * Autogenerated by Thrift Compiler (0.9.3)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package org.apache.airavata.model.appcatalog.accountprovisioning;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import javax.annotation.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked"})
@Generated(value = "Autogenerated by Thrift Compiler (0.9.3)")
public class SSHAccountProvisioner implements org.apache.thrift.TBase<SSHAccountProvisioner, SSHAccountProvisioner._Fields>, java.io.Serializable, Cloneable, Comparable<SSHAccountProvisioner> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("SSHAccountProvisioner");

  private static final org.apache.thrift.protocol.TField NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("name", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField CAN_CREATE_ACCOUNT_FIELD_DESC = new org.apache.thrift.protocol.TField("canCreateAccount", org.apache.thrift.protocol.TType.BOOL, (short)2);
  private static final org.apache.thrift.protocol.TField CAN_INSTALL_SSHKEY_FIELD_DESC = new org.apache.thrift.protocol.TField("canInstallSSHKey", org.apache.thrift.protocol.TType.BOOL, (short)3);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new SSHAccountProvisionerStandardSchemeFactory());
    schemes.put(TupleScheme.class, new SSHAccountProvisionerTupleSchemeFactory());
  }

  private String name; // required
  private boolean canCreateAccount; // required
  private boolean canInstallSSHKey; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    NAME((short)1, "name"),
    CAN_CREATE_ACCOUNT((short)2, "canCreateAccount"),
    CAN_INSTALL_SSHKEY((short)3, "canInstallSSHKey");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // NAME
          return NAME;
        case 2: // CAN_CREATE_ACCOUNT
          return CAN_CREATE_ACCOUNT;
        case 3: // CAN_INSTALL_SSHKEY
          return CAN_INSTALL_SSHKEY;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __CANCREATEACCOUNT_ISSET_ID = 0;
  private static final int __CANINSTALLSSHKEY_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.NAME, new org.apache.thrift.meta_data.FieldMetaData("name", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.CAN_CREATE_ACCOUNT, new org.apache.thrift.meta_data.FieldMetaData("canCreateAccount", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BOOL)));
    tmpMap.put(_Fields.CAN_INSTALL_SSHKEY, new org.apache.thrift.meta_data.FieldMetaData("canInstallSSHKey", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BOOL)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(SSHAccountProvisioner.class, metaDataMap);
  }

  public SSHAccountProvisioner() {
  }

  public SSHAccountProvisioner(
    String name,
    boolean canCreateAccount,
    boolean canInstallSSHKey)
  {
    this();
    this.name = name;
    this.canCreateAccount = canCreateAccount;
    setCanCreateAccountIsSet(true);
    this.canInstallSSHKey = canInstallSSHKey;
    setCanInstallSSHKeyIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public SSHAccountProvisioner(SSHAccountProvisioner other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetName()) {
      this.name = other.name;
    }
    this.canCreateAccount = other.canCreateAccount;
    this.canInstallSSHKey = other.canInstallSSHKey;
  }

  public SSHAccountProvisioner deepCopy() {
    return new SSHAccountProvisioner(this);
  }

  @Override
  public void clear() {
    this.name = null;
    setCanCreateAccountIsSet(false);
    this.canCreateAccount = false;
    setCanInstallSSHKeyIsSet(false);
    this.canInstallSSHKey = false;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void unsetName() {
    this.name = null;
  }

  /** Returns true if field name is set (has been assigned a value) and false otherwise */
  public boolean isSetName() {
    return this.name != null;
  }

  public void setNameIsSet(boolean value) {
    if (!value) {
      this.name = null;
    }
  }

  public boolean isCanCreateAccount() {
    return this.canCreateAccount;
  }

  public void setCanCreateAccount(boolean canCreateAccount) {
    this.canCreateAccount = canCreateAccount;
    setCanCreateAccountIsSet(true);
  }

  public void unsetCanCreateAccount() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __CANCREATEACCOUNT_ISSET_ID);
  }

  /** Returns true if field canCreateAccount is set (has been assigned a value) and false otherwise */
  public boolean isSetCanCreateAccount() {
    return EncodingUtils.testBit(__isset_bitfield, __CANCREATEACCOUNT_ISSET_ID);
  }

  public void setCanCreateAccountIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __CANCREATEACCOUNT_ISSET_ID, value);
  }

  public boolean isCanInstallSSHKey() {
    return this.canInstallSSHKey;
  }

  public void setCanInstallSSHKey(boolean canInstallSSHKey) {
    this.canInstallSSHKey = canInstallSSHKey;
    setCanInstallSSHKeyIsSet(true);
  }

  public void unsetCanInstallSSHKey() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __CANINSTALLSSHKEY_ISSET_ID);
  }

  /** Returns true if field canInstallSSHKey is set (has been assigned a value) and false otherwise */
  public boolean isSetCanInstallSSHKey() {
    return EncodingUtils.testBit(__isset_bitfield, __CANINSTALLSSHKEY_ISSET_ID);
  }

  public void setCanInstallSSHKeyIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __CANINSTALLSSHKEY_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case NAME:
      if (value == null) {
        unsetName();
      } else {
        setName((String)value);
      }
      break;

    case CAN_CREATE_ACCOUNT:
      if (value == null) {
        unsetCanCreateAccount();
      } else {
        setCanCreateAccount((Boolean)value);
      }
      break;

    case CAN_INSTALL_SSHKEY:
      if (value == null) {
        unsetCanInstallSSHKey();
      } else {
        setCanInstallSSHKey((Boolean)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case NAME:
      return getName();

    case CAN_CREATE_ACCOUNT:
      return isCanCreateAccount();

    case CAN_INSTALL_SSHKEY:
      return isCanInstallSSHKey();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case NAME:
      return isSetName();
    case CAN_CREATE_ACCOUNT:
      return isSetCanCreateAccount();
    case CAN_INSTALL_SSHKEY:
      return isSetCanInstallSSHKey();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof SSHAccountProvisioner)
      return this.equals((SSHAccountProvisioner)that);
    return false;
  }

  public boolean equals(SSHAccountProvisioner that) {
    if (that == null)
      return false;

    boolean this_present_name = true && this.isSetName();
    boolean that_present_name = true && that.isSetName();
    if (this_present_name || that_present_name) {
      if (!(this_present_name && that_present_name))
        return false;
      if (!this.name.equals(that.name))
        return false;
    }

    boolean this_present_canCreateAccount = true;
    boolean that_present_canCreateAccount = true;
    if (this_present_canCreateAccount || that_present_canCreateAccount) {
      if (!(this_present_canCreateAccount && that_present_canCreateAccount))
        return false;
      if (this.canCreateAccount != that.canCreateAccount)
        return false;
    }

    boolean this_present_canInstallSSHKey = true;
    boolean that_present_canInstallSSHKey = true;
    if (this_present_canInstallSSHKey || that_present_canInstallSSHKey) {
      if (!(this_present_canInstallSSHKey && that_present_canInstallSSHKey))
        return false;
      if (this.canInstallSSHKey != that.canInstallSSHKey)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_name = true && (isSetName());
    list.add(present_name);
    if (present_name)
      list.add(name);

    boolean present_canCreateAccount = true;
    list.add(present_canCreateAccount);
    if (present_canCreateAccount)
      list.add(canCreateAccount);

    boolean present_canInstallSSHKey = true;
    list.add(present_canInstallSSHKey);
    if (present_canInstallSSHKey)
      list.add(canInstallSSHKey);

    return list.hashCode();
  }

  @Override
  public int compareTo(SSHAccountProvisioner other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetName()).compareTo(other.isSetName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.name, other.name);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetCanCreateAccount()).compareTo(other.isSetCanCreateAccount());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCanCreateAccount()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.canCreateAccount, other.canCreateAccount);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetCanInstallSSHKey()).compareTo(other.isSetCanInstallSSHKey());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCanInstallSSHKey()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.canInstallSSHKey, other.canInstallSSHKey);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("SSHAccountProvisioner(");
    boolean first = true;

    sb.append("name:");
    if (this.name == null) {
      sb.append("null");
    } else {
      sb.append(this.name);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("canCreateAccount:");
    sb.append(this.canCreateAccount);
    first = false;
    if (!first) sb.append(", ");
    sb.append("canInstallSSHKey:");
    sb.append(this.canInstallSSHKey);
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    if (!isSetName()) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'name' is unset! Struct:" + toString());
    }

    if (!isSetCanCreateAccount()) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'canCreateAccount' is unset! Struct:" + toString());
    }

    if (!isSetCanInstallSSHKey()) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'canInstallSSHKey' is unset! Struct:" + toString());
    }

    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class SSHAccountProvisionerStandardSchemeFactory implements SchemeFactory {
    public SSHAccountProvisionerStandardScheme getScheme() {
      return new SSHAccountProvisionerStandardScheme();
    }
  }

  private static class SSHAccountProvisionerStandardScheme extends StandardScheme<SSHAccountProvisioner> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, SSHAccountProvisioner struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.name = iprot.readString();
              struct.setNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // CAN_CREATE_ACCOUNT
            if (schemeField.type == org.apache.thrift.protocol.TType.BOOL) {
              struct.canCreateAccount = iprot.readBool();
              struct.setCanCreateAccountIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // CAN_INSTALL_SSHKEY
            if (schemeField.type == org.apache.thrift.protocol.TType.BOOL) {
              struct.canInstallSSHKey = iprot.readBool();
              struct.setCanInstallSSHKeyIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, SSHAccountProvisioner struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.name != null) {
        oprot.writeFieldBegin(NAME_FIELD_DESC);
        oprot.writeString(struct.name);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(CAN_CREATE_ACCOUNT_FIELD_DESC);
      oprot.writeBool(struct.canCreateAccount);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(CAN_INSTALL_SSHKEY_FIELD_DESC);
      oprot.writeBool(struct.canInstallSSHKey);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class SSHAccountProvisionerTupleSchemeFactory implements SchemeFactory {
    public SSHAccountProvisionerTupleScheme getScheme() {
      return new SSHAccountProvisionerTupleScheme();
    }
  }

  private static class SSHAccountProvisionerTupleScheme extends TupleScheme<SSHAccountProvisioner> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, SSHAccountProvisioner struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      oprot.writeString(struct.name);
      oprot.writeBool(struct.canCreateAccount);
      oprot.writeBool(struct.canInstallSSHKey);
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, SSHAccountProvisioner struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      struct.name = iprot.readString();
      struct.setNameIsSet(true);
      struct.canCreateAccount = iprot.readBool();
      struct.setCanCreateAccountIsSet(true);
      struct.canInstallSSHKey = iprot.readBool();
      struct.setCanInstallSSHKeyIsSet(true);
    }
  }

}

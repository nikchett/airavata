/**
 * Autogenerated by Thrift Compiler (0.10.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package org.apache.airavata.sharing.registry.models;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
/**
 * <p>Field to share sponsorship details</p>
 * <li>sponsorshipClass : (Enum)Type of organization, for example: University, Government Agency, Medical Center, Private lab, Company etc</li>
 * <li>sponsorName : Name of supervisor, manager, group leader or self</li>
 * 
 */
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.10.0)")
public class Sponsorship implements org.apache.thrift.TBase<Sponsorship, Sponsorship._Fields>, java.io.Serializable, Cloneable, Comparable<Sponsorship> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("Sponsorship");

  private static final org.apache.thrift.protocol.TField SPONSORSHIP_CLASS_FIELD_DESC = new org.apache.thrift.protocol.TField("sponsorshipClass", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField SPONSOR_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("sponsorName", org.apache.thrift.protocol.TType.STRING, (short)4);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new SponsorshipStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new SponsorshipTupleSchemeFactory();

  public SponsorshipClass sponsorshipClass; // optional
  public java.lang.String sponsorName; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    SPONSORSHIP_CLASS((short)1, "sponsorshipClass"),
    SPONSOR_NAME((short)4, "sponsorName");

    private static final java.util.Map<java.lang.String, _Fields> byName = new java.util.HashMap<java.lang.String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // SPONSORSHIP_CLASS
          return SPONSORSHIP_CLASS;
        case 4: // SPONSOR_NAME
          return SPONSOR_NAME;
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
      if (fields == null) throw new java.lang.IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(java.lang.String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final java.lang.String _fieldName;

    _Fields(short thriftId, java.lang.String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public java.lang.String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final _Fields optionals[] = {_Fields.SPONSORSHIP_CLASS,_Fields.SPONSOR_NAME};
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.SPONSORSHIP_CLASS, new org.apache.thrift.meta_data.FieldMetaData("sponsorshipClass", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.ENUM        , "SponsorshipClass")));
    tmpMap.put(_Fields.SPONSOR_NAME, new org.apache.thrift.meta_data.FieldMetaData("sponsorName", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(Sponsorship.class, metaDataMap);
  }

  public Sponsorship() {
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public Sponsorship(Sponsorship other) {
    if (other.isSetSponsorshipClass()) {
      this.sponsorshipClass = other.sponsorshipClass;
    }
    if (other.isSetSponsorName()) {
      this.sponsorName = other.sponsorName;
    }
  }

  public Sponsorship deepCopy() {
    return new Sponsorship(this);
  }

  @Override
  public void clear() {
    this.sponsorshipClass = null;
    this.sponsorName = null;
  }

  public SponsorshipClass getSponsorshipClass() {
    return this.sponsorshipClass;
  }

  public Sponsorship setSponsorshipClass(SponsorshipClass sponsorshipClass) {
    this.sponsorshipClass = sponsorshipClass;
    return this;
  }

  public void unsetSponsorshipClass() {
    this.sponsorshipClass = null;
  }

  /** Returns true if field sponsorshipClass is set (has been assigned a value) and false otherwise */
  public boolean isSetSponsorshipClass() {
    return this.sponsorshipClass != null;
  }

  public void setSponsorshipClassIsSet(boolean value) {
    if (!value) {
      this.sponsorshipClass = null;
    }
  }

  public java.lang.String getSponsorName() {
    return this.sponsorName;
  }

  public Sponsorship setSponsorName(java.lang.String sponsorName) {
    this.sponsorName = sponsorName;
    return this;
  }

  public void unsetSponsorName() {
    this.sponsorName = null;
  }

  /** Returns true if field sponsorName is set (has been assigned a value) and false otherwise */
  public boolean isSetSponsorName() {
    return this.sponsorName != null;
  }

  public void setSponsorNameIsSet(boolean value) {
    if (!value) {
      this.sponsorName = null;
    }
  }

  public void setFieldValue(_Fields field, java.lang.Object value) {
    switch (field) {
    case SPONSORSHIP_CLASS:
      if (value == null) {
        unsetSponsorshipClass();
      } else {
        setSponsorshipClass((SponsorshipClass)value);
      }
      break;

    case SPONSOR_NAME:
      if (value == null) {
        unsetSponsorName();
      } else {
        setSponsorName((java.lang.String)value);
      }
      break;

    }
  }

  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case SPONSORSHIP_CLASS:
      return getSponsorshipClass();

    case SPONSOR_NAME:
      return getSponsorName();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case SPONSORSHIP_CLASS:
      return isSetSponsorshipClass();
    case SPONSOR_NAME:
      return isSetSponsorName();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof Sponsorship)
      return this.equals((Sponsorship)that);
    return false;
  }

  public boolean equals(Sponsorship that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_sponsorshipClass = true && this.isSetSponsorshipClass();
    boolean that_present_sponsorshipClass = true && that.isSetSponsorshipClass();
    if (this_present_sponsorshipClass || that_present_sponsorshipClass) {
      if (!(this_present_sponsorshipClass && that_present_sponsorshipClass))
        return false;
      if (!this.sponsorshipClass.equals(that.sponsorshipClass))
        return false;
    }

    boolean this_present_sponsorName = true && this.isSetSponsorName();
    boolean that_present_sponsorName = true && that.isSetSponsorName();
    if (this_present_sponsorName || that_present_sponsorName) {
      if (!(this_present_sponsorName && that_present_sponsorName))
        return false;
      if (!this.sponsorName.equals(that.sponsorName))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetSponsorshipClass()) ? 131071 : 524287);
    if (isSetSponsorshipClass())
      hashCode = hashCode * 8191 + sponsorshipClass.getValue();

    hashCode = hashCode * 8191 + ((isSetSponsorName()) ? 131071 : 524287);
    if (isSetSponsorName())
      hashCode = hashCode * 8191 + sponsorName.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(Sponsorship other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetSponsorshipClass()).compareTo(other.isSetSponsorshipClass());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSponsorshipClass()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.sponsorshipClass, other.sponsorshipClass);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetSponsorName()).compareTo(other.isSetSponsorName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSponsorName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.sponsorName, other.sponsorName);
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
    scheme(iprot).read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public java.lang.String toString() {
    java.lang.StringBuilder sb = new java.lang.StringBuilder("Sponsorship(");
    boolean first = true;

    if (isSetSponsorshipClass()) {
      sb.append("sponsorshipClass:");
      if (this.sponsorshipClass == null) {
        sb.append("null");
      } else {
        sb.append(this.sponsorshipClass);
      }
      first = false;
    }
    if (isSetSponsorName()) {
      if (!first) sb.append(", ");
      sb.append("sponsorName:");
      if (this.sponsorName == null) {
        sb.append("null");
      } else {
        sb.append(this.sponsorName);
      }
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, java.lang.ClassNotFoundException {
    try {
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class SponsorshipStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public SponsorshipStandardScheme getScheme() {
      return new SponsorshipStandardScheme();
    }
  }

  private static class SponsorshipStandardScheme extends org.apache.thrift.scheme.StandardScheme<Sponsorship> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, Sponsorship struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // SPONSORSHIP_CLASS
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.sponsorshipClass = org.apache.airavata.sharing.registry.models.SponsorshipClass.findByValue(iprot.readI32());
              struct.setSponsorshipClassIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // SPONSOR_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.sponsorName = iprot.readString();
              struct.setSponsorNameIsSet(true);
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

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, Sponsorship struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.sponsorshipClass != null) {
        if (struct.isSetSponsorshipClass()) {
          oprot.writeFieldBegin(SPONSORSHIP_CLASS_FIELD_DESC);
          oprot.writeI32(struct.sponsorshipClass.getValue());
          oprot.writeFieldEnd();
        }
      }
      if (struct.sponsorName != null) {
        if (struct.isSetSponsorName()) {
          oprot.writeFieldBegin(SPONSOR_NAME_FIELD_DESC);
          oprot.writeString(struct.sponsorName);
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class SponsorshipTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public SponsorshipTupleScheme getScheme() {
      return new SponsorshipTupleScheme();
    }
  }

  private static class SponsorshipTupleScheme extends org.apache.thrift.scheme.TupleScheme<Sponsorship> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, Sponsorship struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetSponsorshipClass()) {
        optionals.set(0);
      }
      if (struct.isSetSponsorName()) {
        optionals.set(1);
      }
      oprot.writeBitSet(optionals, 2);
      if (struct.isSetSponsorshipClass()) {
        oprot.writeI32(struct.sponsorshipClass.getValue());
      }
      if (struct.isSetSponsorName()) {
        oprot.writeString(struct.sponsorName);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, Sponsorship struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(2);
      if (incoming.get(0)) {
        struct.sponsorshipClass = org.apache.airavata.sharing.registry.models.SponsorshipClass.findByValue(iprot.readI32());
        struct.setSponsorshipClassIsSet(true);
      }
      if (incoming.get(1)) {
        struct.sponsorName = iprot.readString();
        struct.setSponsorNameIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}


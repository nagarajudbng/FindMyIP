package com.codelabs.findmyip.model

import com.google.gson.annotations.SerializedName


data class IpModel (

  @SerializedName("ip"                   ) var ip                 : String?  = null,
  @SerializedName("network"              ) var network            : String?  = null,
  @SerializedName("version"              ) var version            : String?  = null,
  @SerializedName("city"                 ) var city               : String?  = null,
  @SerializedName("region"               ) var region             : String?  = null,
  @SerializedName("region_code"          ) var regionCode         : String?  = null,
  @SerializedName("country"              ) var country            : String?  = null,
  @SerializedName("country_name"         ) var countryName        : String?  = null,
  @SerializedName("country_code"         ) var countryCode        : String?  = null,
  @SerializedName("country_code_iso3"    ) var countryCodeIso3    : String?  = null,
  @SerializedName("country_capital"      ) var countryCapital     : String?  = null,
  @SerializedName("country_tld"          ) var countryTld         : String?  = null,
  @SerializedName("continent_code"       ) var continentCode      : String?  = null,
  @SerializedName("in_eu"                ) var inEu               : Boolean? = null,
  @SerializedName("postal"               ) var postal             : String?  = null,
  @SerializedName("latitude"             ) var latitude           : Double?  = null,
  @SerializedName("longitude"            ) var longitude          : Double?  = null,
  @SerializedName("timezone"             ) var timezone           : String?  = null,
  @SerializedName("utc_offset"           ) var utcOffset          : String?  = null,
  @SerializedName("country_calling_code" ) var countryCallingCode : String?  = null,
  @SerializedName("currency"             ) var currency           : String?  = null,
  @SerializedName("currency_name"        ) var currencyName       : String?  = null,
  @SerializedName("languages"            ) var languages          : String?  = null,
  @SerializedName("country_area"         ) var countryArea        : Int?     = null,
  @SerializedName("country_population"   ) var countryPopulation  : Int?     = null,
  @SerializedName("asn"                  ) var asn                : String?  = null,
  @SerializedName("org"                  ) var org                : String?  = null

)
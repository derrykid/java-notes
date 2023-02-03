## Mock the request:
```java
// PUT method
mockMvc.perform(put("/heavyresource/1")
  .contentType(MediaType.APPLICATION_JSON_VALUE)
  .content(objectMapper.writeValueAsString(
    new HeavyResource(1, "Tom", "Jackson", 12, "heaven street")))
  ).andExpect(status().isOk());


// Patch method
mockMvc.perform(patch("/heavyrecource/1")
  .contentType(MediaType.APPLICATION_JSON_VALUE)
  .content(objectMapper.writeValueAsString(
    new HeavyResourceAddressOnly(1, "5th avenue")))
  ).andExpect(status().isOk());

// Patch method with map
HashMap<String, Object> updates = new HashMap<>();
updates.put("address", "5th avenue");

mockMvc.perform(patch("/heavyresource/1")
    .contentType(MediaType.APPLICATION_JSON_VALUE)
    .content(objectMapper.writeValueAsString(updates))
  ).andExpect(status().isOk());

```

> When a client needs to replace an existing Resource entirely, they can use PUT. When they're doing a partial update, they can use HTTP PATCH.

For instance, when updating a single field of the Resource, sending the complete Resource representation can be cumbersome and uses a lot of unnecessary bandwidth. In such cases, the semantics of PATCH make a lot more sense.

Another important aspect to consider here is idempotence. PUT is idempotent; PATCH can be idempotent but isn't required to be. So, depending on the semantics of the operation we're implementing, we can also choose one or the other based on this characteristic.

## Example

Due to the reason explained above, suppose we have 2 situations: 1: we update the entire entity:

The POJO class:
```java
public class HeavyResource {
    private Integer id;
    private String name;
    private String address;
    // ...
}
```

### Update the entire entity by `PutMapping`
```java
@PutMapping("/heavyresource/{id}")
public ResponseEntity<?> saveResource(@RequestBody HeavyResource heavyResource, @PathVariable("id") String id) {
    heavyResourceRepository.save(heavyResource, id);
    return ResponseEntity.ok("resource saved");
}
```

### Update the partial entity by `PatchMapping`

Now, we only want to update the `address` field, not other fields. We can use `PatchMapping`.

This example, create a custom class `HeavyResourceAddressOnly` is required
```java
@PatchMapping("/heavyresource/{id}")
public ResponseEntity<?> partialUpdateName( @RequestBody HeavyResourceAddressOnly partialUpdate, @PathVariable("id") String id) {
    
    heavyResourceRepository.save(partialUpdate, id);
    return ResponseEntity.ok("resource address updated");
}
```

In that regard, you have to create a lot of small classes to suffice the need of different patches. You can make use of `Map`:
```java
@RequestMapping(value = "/heavyresource/{id}", method = RequestMethod.PATCH, consumes = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<?> partialUpdateGeneric(
  @RequestBody Map<String, Object> updates, @PathVariable("id") String id) {
    
    heavyResourceRepository.save(updates, id);
    return ResponseEntity.ok("resource updated");
}
```

It sends the execution over to service. Under the hood, you might need to write the SQL query to update the entity.
